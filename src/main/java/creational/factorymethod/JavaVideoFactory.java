package creational.factorymethod;

public class JavaVideoFactory implements VideoFactory {
    @Override
    public Video getVideo() {
        return new JavaVideo();
    }
}
