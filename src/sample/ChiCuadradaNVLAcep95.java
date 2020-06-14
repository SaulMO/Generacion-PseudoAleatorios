package sample;

public class ChiCuadradaNVLAcep95
{
    float[] nvlAcept95;

    public ChiCuadradaNVLAcep95(){
        nvlAcept95 = new float[38];
        nvlAcept95[0] =(float) 3.841;
        nvlAcept95[1] =(float) 5.991;
        nvlAcept95[2] =(float) 7.815;
        nvlAcept95[3] =(float) 9.488;
        nvlAcept95[4] =(float) 11.070;
        nvlAcept95[5] =(float) 12.592;
        nvlAcept95[6] =(float) 14.067;
        nvlAcept95[7] =(float) 15.507;
        nvlAcept95[8] =(float) 16.919;
        nvlAcept95[9] =(float) 18.307;
        nvlAcept95[10] =(float) 19.675;
        nvlAcept95[11] =(float) 21.026;
        nvlAcept95[12] =(float) 22.362;
        nvlAcept95[13] =(float) 23.685;
        nvlAcept95[14] =(float) 24.996;
        nvlAcept95[15] =(float) 26.296;
        nvlAcept95[16] =(float) 27.587;
        nvlAcept95[17] =(float) 28.869;
        nvlAcept95[18] =(float) 30.144;
        nvlAcept95[19] =(float) 31.410;
        nvlAcept95[20] =(float) 32.671;
        nvlAcept95[21] =(float) 33.924;
        nvlAcept95[22] =(float) 35.172;
        nvlAcept95[23] =(float) 36.415;
        nvlAcept95[24] =(float) 37.652;
        nvlAcept95[25] =(float) 38.885;
        nvlAcept95[26] =(float) 40.113;
        nvlAcept95[27] =(float) 41.337;
        nvlAcept95[28] =(float) 42.557;
        nvlAcept95[29] =(float) 43.773;
        nvlAcept95[30] =(float) 55.758;
        nvlAcept95[31] =(float) 67.505;
        nvlAcept95[32] =(float) 79.082;
        nvlAcept95[33] =(float) 90.531;
        nvlAcept95[34] =(float) 101.879;
        nvlAcept95[35] =(float) 113.145;
        nvlAcept95[36] =(float) 124.342;
    }

    public float getEstPrueba(int gLibertad){
        if (gLibertad>30 && gLibertad<=39)
            gLibertad = 31;
        if (gLibertad>=40 && gLibertad<49)
            gLibertad = 32;
        if (gLibertad>=50 && gLibertad<59)
            gLibertad = 33;
        if (gLibertad>=60 && gLibertad<69)
            gLibertad = 34;
        if (gLibertad>=70 && gLibertad<79)
            gLibertad = 35;
        if (gLibertad>=80 && gLibertad<89)
            gLibertad = 36;
        if (gLibertad>=90 && gLibertad<99)
            gLibertad = 37;
        float estPrueba;
        switch(gLibertad){
            case 1: estPrueba = nvlAcept95[0]; break;
            case 2: estPrueba = nvlAcept95[1]; break;
            case 3: estPrueba = nvlAcept95[2]; break;
            case 4: estPrueba = nvlAcept95[3]; break;
            case 5: estPrueba = nvlAcept95[4]; break;
            case 6: estPrueba = nvlAcept95[5]; break;
            case 7: estPrueba = nvlAcept95[6]; break;
            case 8: estPrueba = nvlAcept95[7]; break;
            case 9: estPrueba = nvlAcept95[8]; break;
            case 10: estPrueba = nvlAcept95[9]; break;
            case 11: estPrueba = nvlAcept95[10]; break;
            case 12: estPrueba = nvlAcept95[11]; break;
            case 13: estPrueba = nvlAcept95[12]; break;
            case 14: estPrueba = nvlAcept95[13]; break;
            case 15: estPrueba = nvlAcept95[14]; break;
            case 16: estPrueba = nvlAcept95[15]; break;
            case 17: estPrueba = nvlAcept95[16]; break;
            case 18: estPrueba = nvlAcept95[17]; break;
            case 19: estPrueba = nvlAcept95[18]; break;
            case 20: estPrueba = nvlAcept95[19]; break;
            case 21: estPrueba = nvlAcept95[20]; break;
            case 22: estPrueba = nvlAcept95[21]; break;
            case 23: estPrueba = nvlAcept95[22]; break;
            case 24: estPrueba = nvlAcept95[23]; break;
            case 25: estPrueba = nvlAcept95[24]; break;
            case 26: estPrueba = nvlAcept95[25]; break;
            case 27: estPrueba = nvlAcept95[26]; break;
            case 28: estPrueba = nvlAcept95[27]; break;
            case 29: estPrueba = nvlAcept95[28]; break;
            case 30: estPrueba = nvlAcept95[29]; break;
            case 31: estPrueba = nvlAcept95[30]; break;
            case 32: estPrueba = nvlAcept95[31]; break;
            case 33: estPrueba = nvlAcept95[32]; break;
            case 34: estPrueba = nvlAcept95[33]; break;
            case 35: estPrueba = nvlAcept95[34]; break;
            case 36: estPrueba = nvlAcept95[35]; break;
            case 37: estPrueba = nvlAcept95[36]; break;
            default:
                estPrueba =  nvlAcept95[37] = (float) (0.5 * (1.96 + Math.sqrt((2 * gLibertad) - 1))*(1.96 + Math.sqrt((2 * gLibertad) - 1)));
                break;
        }
        return estPrueba;
    }
}