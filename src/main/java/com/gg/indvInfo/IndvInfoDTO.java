package com.gg.indvInfo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Alias("indvinfo")
@Getter
@Setter
@ToString
public class IndvInfoDTO {
    String A;
    String B;
    String C;
    String D;
    String E;
    String F;
    String G;
    String H;
    String I;
    String J;
    String K;
    String L;
    String M;
    String N;
    String O;
    String P;
    float score;

    public IndvInfoDTO() {
    }

    public IndvInfoDTO(String A, String B, String C, String D, String E, String F, String G, String H, String I,
            String J, String K, String L, String M, String N, String O, String P, float score) {
        super();
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
        this.E = E;
        this.F = F;
        this.G = G;
        this.H = H;
        this.I = I;
        this.J = J;
        this.K = K;
        this.L = L;
        this.M = M;
        this.N = N;
        this.O = O;
        this.P = P;
        this.score = score;
    }
}
