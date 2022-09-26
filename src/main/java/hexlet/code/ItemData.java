package hexlet.code;

public class ItemData<F, L> {
    private String status; //without, modified, removed, added
    private F first;
    private L last;

    public ItemData(String statusCur, F firstObj, L lastObj) {
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
