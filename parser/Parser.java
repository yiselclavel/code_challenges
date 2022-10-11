import java.util.ArrayList;

public class Parser {
    public static ArrayList<ArrayList<String>> parseCsv(
            String csv,
            String separator,
            String quote
    ) {System.out.println(quote);

        if (separator.equals("")) separator = ",";
        if (separator.length() > 1) System.err.println("Expected one character");
        if (quote.equals("")) quote = "\"";
        if (quote.length() > 1) System.err.println("Expected one character");
        if (separator.equals(".")) separator = "\\.";
        if (quote.equals("$")) quote = "\\\\$";
        if (quote.equals("\\")) quote = "\\\\";
        System.out.println(separator);
        System.out.println(quote+quote);
        //System.out.println(csv);

//        final String regex = "\n(\"+quote+\"[^+quote+\"]*+quote+\"|[^\n]*|)";
//        final Pattern pattern = Pattern.compile(regex);
//        final Matcher matcher = pattern.matcher("\n" + csv); // le agregamos una coma al texto para que coincida con el primer elemento

        ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
        if (csv.contains(quote)) {
            String[] rows = csv.split("\n(?=(?:[^"+quote+"]*"+quote+"[^"+quote+"]*"+quote+")*[^"+quote+"]*$)", -1);
            for(String r : rows) {
                ArrayList<String> a = new ArrayList<String>();
                String[] fields = r.split(separator);
                for (int i = 0; i < fields.length; i++) {
                    if (fields[i].startsWith(quote) && fields[i].endsWith(quote)){
                        System.out.println(fields[i]);
                        String s = fields[i].substring(1,fields[i].length()-1);
                        System.out.println(s);
                        a.add(s);
                    } else if (fields[i].startsWith(quote)){
                        String s = fields[i].substring(1,fields[i].length());
                        System.out.println(s);
                        a.add(s);
                    } else if (fields[i].endsWith(quote)){
                        String s = fields[i].substring(0,fields[i].length()-1);
                        System.out.println(s);
                        a.add(s);
                    }else if(fields[i].contains(quote+quote)) {
                      String s = fields[i].replace(quote+quote,quote);
                        a.add(s);
                    } else {
                        a.add(fields[i]);
                    }
                }
                array.add(a);
            }

//
//            while (matcher.find()) {
//                //System.out.print  ("Elemento " + ++n + ": ");
//                ArrayList<String> a = new ArrayList<String>();
//                System.out.println(matcher.group(1));
//                System.out.println(matcher.group(2));
//                String row = matcher.group(1);
//                String[] fields = row.split(separator);
//                for (int i = 0; i < fields.length; i++) {
//                    a.add(fields[i]);
//                }
//                array.add(a);
//            }
        } else{
                String[] rows = csv.split("\n");
                for (int i = 0; i < rows.length; i++) {
                    ArrayList<String> a = new ArrayList<String>();
                    String[] fields = rows[i].split(separator);
                    for (int j = 0; j < fields.length; j++) {
                        //System.out.println(fields[j]);
                        a.add(fields[j]);
                    }
                    array.add(a);
                }
            }

            return array;
        }

        public static void main (String[]args){
            //  System.out.println(Challenge.parseCsv("1, ,\"3\n4\",5, ", ",", "\""));

            System.out.println(Parser.parseCsv("one,\\$two wraps\nonto \\$two\\$ lines\\$,three, \n4,,6, ", ",", "\""));
            //    System.out.println(Challenge.parseCsv("one,\"two wraps\nonto \"two\" lines\",three\n4,,6", ",", "\""));
        }
    }
