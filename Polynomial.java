import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;

public class Polynomial {
    public double[] coefficients;
    public int[] exponents;


    public Polynomial() {
        this.coefficients = new double[]{0};
        this.exponents = new int[]{0};
    }

    public Polynomial(double[] arr1, int[] arr2) {
        this.coefficients = arr1;
        this.exponents = arr2;
    }

    public Polynomial add(Polynomial poly) {
        Polynomial s = new Polynomial();
    
        if (this.coefficients.length >= poly.coefficients.length) {
            s.coefficients = this.coefficients;
            s.exponents = this.exponents;
            for (int i = 0; i < poly.exponents.length; i++) {
                for (int j = 0; j < s.exponents.length; j++) {
                    if (poly.exponents[i] == s.exponents[j]) {
                        s.coefficients[j] += poly.coefficients[i];
                        break;
                    }
                    else if (j == s.exponents.length - 1) {
                        int newexponents[] = new int[s.exponents.length + 1];
                        for (int k = 0; k < s.exponents.length; k++) {
                            newexponents[k] = s.exponents[k];
                        }

                        newexponents[newexponents.length - 1] = poly.exponents[i];
                        s.exponents = newexponents;


                        double newcoefficients[] = new double[s.coefficients.length + 1];
                        for (int k = 0; k < s.coefficients.length; k++) {
                            newcoefficients[k] = s.coefficients[k];
                        }

                        newcoefficients[newcoefficients.length - 1] = poly.coefficients[i];
                        s.coefficients = newcoefficients;
                        break;
                    }
                }
            }
        }


        if (poly.coefficients.length > this.coefficients.length) {
            s.coefficients = poly.coefficients;
            s.exponents = poly.exponents;
            for (int i = 0; i < this.exponents.length; i++) {
                for (int j = 0; j < s.exponents.length; j++) {
                    if (this.exponents[i] == s.exponents[j]) {
                        s.coefficients[j] += this.coefficients[i];
                        break;
                    }
                    else if (j == s.exponents.length - 1) {
                        int newexponents[] = new int[s.exponents.length + 1];
                        for (int k = 0; k < newexponents.length - 1; k++) {
                            newexponents[k] = s.exponents[k];
                        }

                        newexponents[newexponents.length - 1] = this.exponents[i];
                        s.exponents = newexponents;


                        double newcoefficients[] = new double[s.coefficients.length + 1];
                        for (int k = 0; k < s.coefficients.length; k++) {
                            newcoefficients[k] = s.coefficients[k];
                        }

                        newcoefficients[newcoefficients.length - 1] = this.coefficients[i];
                        s.coefficients = newcoefficients;
                        break;
                    }
                }
            }
        }
        int occurences = 0;
        for (int i = 0; i < s.coefficients.length; i++) {
            if (s.coefficients[i] == 0) {
                occurences++;
            }
        }

        int[] anotherexponents = new int[s.exponents.length - occurences];
        double[] anothercoefficients = new double[s.coefficients.length - occurences];
        int newindex = 0;

        for (int i = 0; i < s.coefficients.length; i++) {
            if (s.coefficients[i] == 0) {
                continue;
            } else {
                anotherexponents[newindex] = s.exponents[i];
                anothercoefficients[newindex] = s.coefficients[i];
                newindex++;
            }

        }

        s.exponents = anotherexponents;
        s.coefficients = anothercoefficients;

        return s;
    }

    public double evaluate(double num) {
        double answer = 0;

        for (int i = 0; i < this.coefficients.length; i++) {
            answer += this.coefficients[i] * Math.pow(num,this.exponents[i]);
        }
        return answer;
    }

    public boolean hasRoot(double num) {
        if (evaluate(num) == 0) {
            return true;
        }
        return false; 
    }

    public boolean checker(int num, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return true;
            }
        }
        return false;
    }

    public Polynomial multiply(Polynomial poly) {
        Polynomial s = new Polynomial();


        double[] answercoefficients = new double[this.exponents.length * poly.coefficients.length];
        int[] answerexponents = new int[this.exponents.length * poly.coefficients.length];

        int count_coefficients = 0;
        int count_exponents = 0;


        for (int i = 0; i < poly.coefficients.length; i++) {
            for (int j = 0; j < this.coefficients.length; j++) {
                answercoefficients[count_coefficients] = poly.coefficients[i] * this.coefficients[j];
                answerexponents[count_exponents] = poly.exponents[i] + this.exponents[j];
                count_coefficients++;
                count_exponents++; 
            }
        } 

        s.coefficients = answercoefficients;
        s.exponents = answerexponents;  

        
        
        int simplifications = 0; 

        for (int i = 0; i < s.coefficients.length; i++) {
            for (int j = i + 1; j < s.coefficients.length; j++) {
                if (s.exponents[i] == s.exponents[j]) {
                    simplifications++;
                }
            }
        }

        double[] simplifycoefficients = new double[s.coefficients.length - simplifications];
        int[] simplifyexponents = new int[s.exponents.length - simplifications];

        int index = 0;
        int number_counter = 0;
        double new_coefficient = 0;

        for (int i = 0; i < s.coefficients.length; i++) {
            for (int j = i + 1; j < s.coefficients.length; j++) {

                if (checker(s.exponents[i], simplifyexponents) == true) {
                    index--;
                    break;
                }

                if (s.exponents[i] == s.exponents[j]) {
                    new_coefficient = s.coefficients[i] + s.coefficients[j];
                    number_counter++;
                    
                }
                if (j == s.coefficients.length - 1 && number_counter != 0) {
                    simplifycoefficients[index] = new_coefficient;
                    simplifyexponents[index] = s.exponents[i];
                }

                if (j == s.coefficients.length - 1 && number_counter == 0) {
                    simplifycoefficients[index] = s.coefficients[i];
                    simplifyexponents[index] = s.exponents[i];
                }  
 
            }
            index++;
            new_coefficient = 0;
            number_counter = 0;
        }

        if (checker(s.exponents[s.exponents.length - 1], simplifyexponents) == false) {
            simplifycoefficients[simplifycoefficients.length - 1] = s.coefficients[s.exponents.length - 1];
            simplifyexponents[simplifycoefficients.length - 1] = s.exponents[s.exponents.length - 1];
        }


        s.coefficients = simplifycoefficients;
        s.exponents = simplifyexponents;

        int occurences = 0;
        for (int i = 0; i < s.coefficients.length; i++) {
            if (s.coefficients[i] == 0) {
                occurences++;
            }
        }

        int[] anotherexponents = new int[s.exponents.length - occurences];
        double[] anothercoefficients = new double[s.coefficients.length - occurences];
        int newindex = 0;

        for (int i = 0; i < s.coefficients.length; i++) {
            if (s.coefficients[i] == 0) {
                continue;
            } else {
                anotherexponents[newindex] = s.exponents[i];
                anothercoefficients[newindex] = s.coefficients[i];
                newindex++;
            }

        }

        s.exponents = anotherexponents;
        s.coefficients = anothercoefficients;

        return s;
    }
    
    public Polynomial(File poly_file) throws IOException{
        try {
            Polynomial s = new Polynomial();
            Scanner input = new Scanner(poly_file);

            while (input.hasNextLine()) {

            String data = input.nextLine();
            double[] string_coefficients = new double[data.length()];
            int[] string_exponents = new int[data.length()];
            int coefficient_index = 0;
            int exponent_index = 0;


            for (int i = 0; i < data.length(); i++) {
                if (i == 0) {
                    if (data.charAt(i) == '-') {
                        string_coefficients[coefficient_index] = (-1.0) * Double.parseDouble(Character.toString(data.charAt(i + 1)));
                        string_exponents[exponent_index] = 0;
                        exponent_index++;
                        coefficient_index++;
                        continue;
                    }
                    else if (data.charAt(i) != '-') {
                        string_coefficients[coefficient_index] = Double.parseDouble(Character.toString(data.charAt(i)));
                        string_exponents[exponent_index] = 0;
                        exponent_index++;
                        coefficient_index++;
                        continue;
                    }

                }
                if (Character.isDigit(data.charAt(i))) {
                    if (Character.isLetter(data.charAt(i - 1))) {
                        string_exponents[exponent_index] = Integer.parseInt(Character.toString(data.charAt(i)));
                        exponent_index++;
                        continue;
                    }
                    else if (Character.isLetter(data.charAt(i + 1))) {
                        string_coefficients[coefficient_index] = Double.parseDouble(Character.toString(data.charAt(i)));
                        if (data.charAt(i - 1) == '-') {
                            string_coefficients[coefficient_index] *= (-1.0);
                        }
                        coefficient_index++;
                        continue;  
                    }
                    else if (!Character.isDigit(data.charAt(i - 1)) && !Character.isLetter(data.charAt(i + 1))) {
                        if (data.charAt(i - 1) == '-') {
                            string_coefficients[coefficient_index] = Double.parseDouble(Character.toString(data.charAt(i)));
                            string_coefficients[coefficient_index] *= (-1.0);
                            coefficient_index++;
                            continue;
                        }
                        else {
                            string_coefficients[coefficient_index] = Double.parseDouble(Character.toString(data.charAt(i)));
                            coefficient_index++;
                            continue;
                        }
                    }
                }   
            }

            int occurences = 0;
            for (int i = 0; i < string_coefficients.length; i++) {
                if (string_coefficients[i] == 0) {
                occurences++;
                }
            }

            int[] anotherexponents = new int[string_exponents.length - occurences];
            double[] anothercoefficients = new double[string_coefficients.length - occurences];
            int newindex = 0;

            for (int i = 0; i < string_coefficients.length; i++) {
                if (string_coefficients[i] == 0) {
                    continue;
                } else {
                    anotherexponents[newindex] = string_exponents[i];
                    anothercoefficients[newindex] = string_coefficients[i];
                    newindex++;
                }
            }


            this.coefficients = anothercoefficients;
            this.exponents = anotherexponents;

        }
        input.close();


        
    } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();

    }        
    }
    
    public void saveToFile(String fileName) throws IOException {

        try {
            FileWriter my_writer = new FileWriter(fileName);

            for (int i = 0; i < this.coefficients.length; i++) {
                if (this.coefficients[i] == 0) {
                    continue;
                } else if (this.coefficients[i] > 0 && i > 0) {
                    my_writer.write("+");

                }

                if (this.coefficients[i] != 0.0) {
                    my_writer.write(String.valueOf(this.coefficients[i]));
                }

                if (this.exponents[i] != 0) {
                    my_writer.write("x");
                    my_writer.write(String.valueOf(this.exponents[i])); 
                }
            }
        
        my_writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
