public class cord {
        private int x;
        private int y;
        public cord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            cord point = (cord) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return x * 31 + y;
        }
    }