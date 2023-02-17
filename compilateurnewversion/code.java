package compilateurnewversion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;

public class code {

    public static HashMap<String, String> data = new HashMap<String, String>();

    public static boolean isInit(String instruction) {
        Pattern pattern = Pattern.compile("<id,[a-z\\d]+?><oppaff,:=><nombre,[\\d]+?>", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(instruction);
        return matcher.find();
    }

    public static String mov(String a, String b) {
        return "    mov " + a + ", " + b;
    }

    public static String op(String a, String b, String op) {
        return "    " + op + " " + a + ", " + b;
    }

    public static String initVariable(String var, String val) {
        String asm = new String();
        asm += mov("eax", "'" + val + "'"); // mov eax, '1'
        asm += "\n";
        asm += mov("[" + var + "]", "eax"); // mov [a], eax
        return asm;
    }

    public static String movVariableTotVariable(String var, String val) {
        String asm = new String();
        asm += mov("eax", "[" + val + "]"); // mov eax, [b]
        asm += "\n";
        asm += mov("[" + var + "]", "eax"); // mov [a], eax
        return asm;
    }

    public static void main(String[] args) {

        String s = "<id,a><oppaff,:=><nombre,1><pv,;><id,c><oppaff,:=><nombre,1><pv,;><id,b><oppaff,:=><nombre,2><pv,;><id,a><oppaff,:=><id,b><op,add><nombre,2><op,sub><id,c><pv,;>";
        String instructions[] = s.split("<pv,;>");

        for (String instruction : instructions) {
            if (isInit(instruction)) {
                System.out.println(initVariable(getFirstId(instruction), getNumber(instruction)));
            } else if (isOperation(instruction)) {
                // System.out.println("OP");
                String resultVar = getFirstId(instruction);
                String operation = instruction.split("<oppaff,:=>", 0)[1];
                String[] operandas = operation.split("<op,|<", 0);
                // System.out.println(operation);
                // System.out.println("----------");
                String pendingOperation = null;
                for (String op : operandas) {
                    // System.out.println("op : " + op);
                    if (pendingOperation == null && isId(op)) {
                        // System.out.println("mov " + getFirstId(op) + " to var");
                        System.out.println(movVariableTotVariable(resultVar, getFirstId(op)));
                    } else if (pendingOperation == null && isNumber(op)) {
                        // System.out.println("mov number to var");
                        System.out.println(initVariable(resultVar, getNumber(op)));
                    } else if (startsWithOpertion(op)) {
                        pendingOperation = getOperation(op);

                    } else if (isId(op)) {
                        // System.out.println(pendingOperation + " " + getFirstId(op));
                        System.out.println(mov("ebx", "[" + getFirstId(op) + "]"));
                        System.out.println(op("eax", "ebx", pendingOperation));

                    } else if (isNumber(op)) {
                        // System.out.println("num");
                        // System.out.println(pendingOperation + " " + getNumber(op));
                        System.out.println(mov("ebx", "'" + getNumber(op) + "'"));
                        System.out.println(op("eax", "ebx", pendingOperation));
                    }

                    // Steps : move first(number/id) into returnVariable
                    // boucle :
                    // if operation save in variable
                    // if id/number do operation then mov result to returnVariable
                }
                System.out.println(mov("[" + resultVar + "]", "eax"));

            }
        }
    }

    private static boolean startsWithOpertion(String instruction) {
        Pattern pattern = Pattern.compile("^(add|sub|mult|div|mod)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(instruction);
        return matcher.find();
    }

    private static String getOperation(String instruction) {
        Pattern pattern = Pattern.compile("^(add|sub|mult|div|mod)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(instruction);
        matcher.find();
        return matcher.group();
    }

    private static boolean isId(String instruction) {
        Pattern pattern = Pattern.compile("id,", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(instruction);
        return matcher.find();
    }

    private static boolean isNumber(String instruction) {
        Pattern pattern = Pattern.compile("nombre,", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(instruction);
        return matcher.find();
    }

    private static boolean isOperation(String instruction) {
        Pattern pattern = Pattern.compile("op,", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(instruction);
        return matcher.find();
    }

    private static String getFirstId(String instruction) {
        Integer start = instruction.indexOf("id,") + 3;
        int end = instruction.indexOf(">");
        return instruction.substring(start, end);
    }

    private static String getNumber(String instruction) {
        Integer start = instruction.indexOf("nombre,") + 7;
        int end = instruction.length() - 1;
        return instruction.substring(start, end);
    }
}
