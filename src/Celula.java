public class Celula {
    private int edad;
    private boolean viva;

    public Celula() {
        this.edad = 0;
        this.viva = false;
    }

    public Celula(boolean var1) {
        this.edad = var1 ? 1 : 0;
        this.viva = var1;
    }

    public void nacer() {
        this.viva = true;
        this.edad = 1;
    }

    public void morir() {
        this.viva = false;
        this.edad = 0;
    }

    public void envejecer() {
        if (this.viva) {
            ++this.edad;
        }

    }

    public boolean estaViva() {
        return this.viva;
    }

    public int getEdad() {
        return this.edad;
    }

    public String toString() {
        if (!this.viva) {
            return "·";
        } else {
            return this.edad < 10 ? String.valueOf(this.edad) : "*";
        }
    }
}