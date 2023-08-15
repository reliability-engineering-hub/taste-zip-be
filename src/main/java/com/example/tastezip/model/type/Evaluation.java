package com.example.tastezip.model.type;

public enum Evaluation {
    VERY_GOOD(1),
    GOOD(2),
    SOSO(3),
    BAD(4),
    VERY_BAD(5);

    private Integer evalution;

    Evaluation(Integer evalution){
        this.evalution = evalution;
    }

    public Integer getEvalution(){
        return evalution;
    }

    public static Evaluation fromInteger(Integer value){
        for (Evaluation e : Evaluation.values()){
            if (e.evalution.equals(value)){
                return e;
            }
        }
        throw new IllegalArgumentException("UnKnown evaluation value : " + value);
    }
}
