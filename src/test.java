public class test {


    public static void main(String[] args) {
        try{
            throw new OutOfMemoryError();
        }
        catch (OutOfMemoryError e){
            System.out.println(1);
            System.exit(131);
        }
        finally {
            System.out.println(2);
        }
    }
}
