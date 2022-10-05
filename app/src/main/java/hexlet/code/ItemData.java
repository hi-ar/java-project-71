package hexlet.code;

public class ItemData {
    private String status; //unchanged, changed, removed, added
    private Object first;
    private Object last;

    public ItemData(String statusCur, Object firstObj, Object lastObj) {
        this.status = statusCur;
        this.first = firstObj;
        this.last = lastObj;
    }

    public final String getStatus() {
        return status;
    }

    public final Object getFirst() {
        return first;
    }

    public final Object getLast() {
        return last;
    }

}
