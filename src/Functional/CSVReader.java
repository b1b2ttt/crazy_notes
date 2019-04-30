package Functional;

public class CSVReader
{
    private CSVReader(){}

    public static CSVCipic getCipic(String name)
    {
        return new CSVCipic(name);
    }
}
