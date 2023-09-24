public class Polynomial {
    public double[] coefficients;

    public Polynomial() {
        this.coefficients = new double[]{0};
    }

    public Polynomial(double[] arr) {
        this.coefficients = arr;
    }

    public Polynomial add(Polynomial poly) {
        Polynomial s = new Polynomial();

        if (this.coefficients.length >= poly.coefficients.length) {
            s.coefficients = this.coefficients;
            for (int i = 0; i < poly.coefficients.length; i++) {
                s.coefficients[i] += poly.coefficients[i]; 
            }
        }

        if (poly.coefficients.length > this.coefficients.length) {
            s.coefficients = poly.coefficients;
            for (int i = 0; i < this.coefficients.length; i++) {
                 s.coefficients[i] += this.coefficients[i]; 
            }
        }
        return s;
    }

    public double evaluate(double num) {
        double answer = 0;

        for (int i = 0; i < this.coefficients.length; i++) {
            answer += this.coefficients[i] * Math.pow(num, i);
        }
        return answer;
    }

    public boolean hasRoot(double num) {
        if (evaluate(num) == 0) {
            return true;
        }
        return false; 
    }
}

