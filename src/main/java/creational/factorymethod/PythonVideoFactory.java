package creational.factorymethod;

public class PythonVideoFactory implements VideoFactory {
    @Override
    public Video getVideo() {
        return new PythonVideo();
    }
}
