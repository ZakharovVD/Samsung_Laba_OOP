import java.util.ArrayList;
import java.util.Scanner;

abstract class Bicycle {
    protected int wheel_count;
    protected int wheel_radius;
    protected boolean is_repair = false;

    Bicycle(int k, int r){
        this.wheel_radius = r;
        this.wheel_count = k;
    }
    public void buyBicycle(){
        System.out.println("Поздравляем. Вы купили веласипед");
    }
    public int getLen(){
        return wheel_count * wheel_radius;
    }

    abstract void repair();
    abstract void use();
    abstract void assemble();
    abstract String info();

    protected String info_tmp(){
        return (", " + (is_repair ? "" : "not ") + "repaired, summary radius " + getLen());
    }
}

class One_Wheel_Bicycle extends Bicycle {
    One_Wheel_Bicycle(int r) {
        super(1, r);
    }
    @Override
    public void repair() {
        System.out.println("Ремонт одноколесного велосипеда");
        is_repair = true;
    }
    @Override
    public void use() {
        System.out.println("Если крутить педали, одноколесный велосипед поедет. Главное не упасть");
    }
    @Override
    public void assemble() {
        System.out.println("Одноколесный велосипед собран");
    }
    public String info(){
        return ("one wheel" + info_tmp());
    }
}

class Two_Wheel_Bicycle extends Bicycle {
    Two_Wheel_Bicycle(int r) {
        super(2, r);
    }
    @Override
    public void repair() {
        System.out.println("Ремонт двухколесного велосипеда");
        is_repair = true;
    }
    @Override
    public void use() {
        System.out.println("Если крутить педали, двухколесный велосипед поедет");
    }
    @Override
    public void assemble() {
        System.out.println("Двухколесный велосипед собран");
    }

    public String info(){
        return ("two wheels" + info_tmp());
    }
}

class Three_Wheel_Bicycle extends Bicycle {
    Three_Wheel_Bicycle(int r) {
        super(3, r);
    }
    @Override
    public void repair() {
        System.out.println("Ремонт трехколесного велосипеда");
        is_repair = true;
    }
    @Override
    public void use() {
        System.out.println("Если крутить педали, трехколесный велосипед поедет");
    }
    @Override
    public void assemble() {
        System.out.println("Трехколесный велосипед собран");
    }
    public String info(){
        return ("three wheels" + info_tmp());
    }
}

class Los_Santos_Custom{
    public static void repair_bicycle(Bicycle bicycle){
        if(bicycle.is_repair) System.out.print("Уже  отремонтирован\n");
        else bicycle.repair();
    }
}

class main{
    public static void main(String[] args) {
        /*
        *   В модели все велосипеды унаследовываются от абстрактного класса с уже описанными полями,
        *   которые одинаковы, и должны быть у каждого велосипеда.
        *   Интерфейсы не используются в целях экономии размеров кода.
        */
        Two_Wheel_Bicycle cj = new Two_Wheel_Bicycle(17);
        cj.assemble();
        cj.use();
        cj.repair();
        System.out.print("\n\n");


        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<Bicycle> bicycles = new ArrayList<>();
        for(int i = 0; i < n; i++){
            int r = (int)(Math.random() * 1000) % 10 + 10;
            int k = (int)(Math.random() * 1000) % 3 + 1;

            if(k == 1) bicycles.add(new One_Wheel_Bicycle(r));
            if(k == 2) bicycles.add(new Two_Wheel_Bicycle(r));
            if(k == 3) bicycles.add(new Three_Wheel_Bicycle(r));
        }

        for(Bicycle bicycle : bicycles){
            System.out.println(bicycle.info());
        } System.out.print("\n\n");

        for(Bicycle bicycle : bicycles){
            if(bicycle instanceof One_Wheel_Bicycle){
                ((One_Wheel_Bicycle)bicycle).repair();
            }
        }

        for(Bicycle bicycle : bicycles){
            System.out.println(bicycle.info());
        } System.out.print("\n\n");

        for(int i = 0; i < n; i++){
            for(int j = 1; j < n; j++){
                if(bicycles.get(j).getLen() < bicycles.get(j - 1).getLen()){
                    Bicycle tmp = bicycles.get(j);
                    bicycles.set(j, bicycles.get(j - 1));
                    bicycles.set(j - 1, tmp);
                }
            }
        }

        for(Bicycle bicycle : bicycles){
            System.out.println(bicycle.info());
        } System.out.print("\n\n");

        for(Bicycle bicycle : bicycles){
            Los_Santos_Custom.repair_bicycle(bicycle);
        }

        for(Bicycle bicycle : bicycles){
            System.out.println(bicycle.info());
        } System.out.print("\n\n");
    }
}