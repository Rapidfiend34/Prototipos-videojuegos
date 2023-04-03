
    public abstract class Celda {
        private boolean solido;

        public Celda(boolean solido) {
            this.solido = solido;
        }

        public boolean esSolido() {
            return solido;
        }

        public abstract char getCaracter();
    }


