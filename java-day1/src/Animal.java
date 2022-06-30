public class Animal {
    private String type;
    private int foot;

    public Animal(String type, int foot) {
        this.type = type;
        this.foot = foot;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFoot() {
        return foot;
    }

    public void setFoot(int foot) {
        this.foot = foot;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "type='" + type + '\'' +
                ", foot=" + foot +
                '}';
    }
}
