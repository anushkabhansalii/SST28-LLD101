package pen;

public interface WritingInstrument {
    void start() throws Exception;
    void write(String s) throws Exception;
    void close() throws Exception;
    void refill(String color) throws Exception;
}
