package hello.advanced.trace;

import java.util.UUID;

public class TraceId {
    private String id;
    private int level;

    public TraceId() {
        this.id = createId();
        this.level = 0;
    }
    private TraceId(String id, int level){
        this.id = id;
        this.level = level;
    }
    private String createId() {
        return UUID.randomUUID().toString().substring(0, 8);//uuid 넘 길어서 앞 8자리만 쓸거임.
    }

    public TraceId createNexId(){
        return new TraceId(id, level+1);
    }

    public TraceId previousNexId(){
        return new TraceId(id, level-1);
    }

    public boolean isFirstLevel() {
        return level == 0;
    }

    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }
}
