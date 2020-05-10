package xyz.xindoo.re.nfa;

import xyz.xindoo.re.Constant;
import xyz.xindoo.re.State;

public class NFAGraph {
    public State start;
    public State end;
    public NFAGraph(State start, State end) {
        this.start = start;
        this.end = end;
    }

    // |
    public void addParallelGraph(NFAGraph NFAGraph) {
        State newStart = new State();
        State newEnd = new State();
        newStart.addNext(Constant.EPSILON, this.start);
        newStart.addNext(Constant.EPSILON, NFAGraph.start);
        this.end.addNext(Constant.EPSILON, newEnd);
        NFAGraph.end.addNext(Constant.EPSILON, newEnd);
        this.start = newStart;
        this.end = newEnd;
    }

    //
    public void addSeriesGraph(NFAGraph NFAGraph) {
        this.end.addNext(Constant.EPSILON, NFAGraph.start);
        this.end = NFAGraph.end;
    }

    // * 重复0-n次
    public void repeatStar() {
        repeatPlus();
        addSToE(); // 重复0
    }

    // ? 重复0次哦
    public void addSToE() {
        start.addNext(Constant.EPSILON, end);
    }

    // + 重复1-n次
    public void repeatPlus() {
        State newStart = new State();
        State newEnd = new State();
        newStart.addNext(Constant.EPSILON, this.start);
        end.addNext(Constant.EPSILON, newEnd);
        end.addNext(Constant.EPSILON, start);
        this.start = newStart;
        this.end = newEnd;
    }

}