package enums;

/**
 * @author Artyom
 * @version 2.0
 */
public class EnumSwitch {
    //test
    private static final double finalBid_0_1 = 0.1; //2
    private static final double finalBid_0_11 = 0.11; //2
    private static final double finalBid_0_2 = 0.2; //2
    private static final double finalBid_0_3 = 0.3; //3
    private static final double finalBid_2 = 2; //7

    private static final double condition_4000000 = 4000000; //4 -> 2
    private static final double condition_7000000 = 7000000; //4 ->2
    private static final double condition_10000000 = 10000000; //6 -> 4
    private static final double condition_20000000 = 20000000; //2
    private static final double condition_50000000 = 50000000; //2
    private static final double condition_300000000 = 300000000; //10 -> 4

    // Объявление числовой переменной, хранящей значение налоговой ставки
    public static double finalBid;

    // Объявление переменной для хранения значения кадастровой стоимости объекта налогообложения
    public static int cadastralValue;

    public double getFinalBid() {
        return finalBid;
    }

    /**
     * //Поменять местами константы
     * FIXME убрать Магические числа
     */
    public static void enumUse(int regionIndex, int propertyIndex) {
        //FIXME Вынести в сервлет чтобы суммировало
        int sum = propertyIndex + regionIndex;
        creat(ReaderCoff.getMassCoff().get(sum));
    }

    public static void creat(int ordinal) {
        cityProperty day = cityProperty.values()[ordinal];
        finalBid =  day.changeFinalBid();
    }

    /**
     * Вложенный класс перечислений cityProperty
     */
    enum cityProperty  {
        UfaRoom_UfaApartment() {  //0-1
            public double changeFinalBid() {
                System.out.println("UfaRoom_UfaApartment");
                double finalBidNoMagic_0_165 = 0.165;
                double finalBidNoMagic_0_198 = 0.198;
                double finalBidNoMagic_0_22 = 0.22;
                //FIXME 100000000 ---> 10000000 УБРАЛ  ОДИН 0 для бывшего UfaRoom
                return UfaRoom_UfaApartment_UfaHouse(finalBid_0_11, finalBidNoMagic_0_165, finalBidNoMagic_0_198, finalBidNoMagic_0_22);
            }
        },
        UfaHouse { //2
            public double changeFinalBid() {
                System.out.println("UfaHouse");
                double finalBidNoMagic_0_12 = 0.12;
                double finalBidNoMagic_0_18 = 0.18;
                double finalBidNoMagic_0_216 = 0.216;
                double finalBidNoMagic_0_24 = 0.24;
                return UfaRoom_UfaApartment_UfaHouse(finalBidNoMagic_0_12, finalBidNoMagic_0_18, finalBidNoMagic_0_216, finalBidNoMagic_0_24);
            }
        },
        UfaCar { //3
            public double changeFinalBid() {
                System.out.println("UfaCar");
                return UfaCar_UfaOthers_KazanOthers_MoscowOthers(finalBid_2, finalBid_0_11);
            }
        },
        UfaOthers_KazanOthers_MoscowOthers { //4 8 13
            public double changeFinalBid() {
                System.out.println("UfaOthers_KazanOthers_MoscowOthers");
                double finalBidNoMagic_0_5 = 0.5; //1
                return UfaCar_UfaOthers_KazanOthers_MoscowOthers(finalBid_2, finalBidNoMagic_0_5);
            }
        },
        KazanRoom_KazanApartment { //5
            public double changeFinalBid() {
                System.out.println("KazanRoom_KazanApartment");
                return UfaCar_UfaOthers_KazanOthers_MoscowOthers(finalBid_2, finalBid_0_2);
            }
        },
        KazanHouse_KazanCar { //6
            public double changeFinalBid() {
                System.out.println("KazanHouse_KazanCar");
                return UfaCar_UfaOthers_KazanOthers_MoscowOthers(finalBid_2, finalBid_0_3);
            }
        },
        MoscowRoom_MoscowApartment_MoscowHouse { //9 10 11
            public double changeFinalBid() {
                double finalBidNoMagic_0_15 = 0.15; //1

                System.out.println("MoscowRoom_MoscowApartment_MoscowHouse");
                if (cadastralValue <= condition_10000000) {
                    return finalBid_0_1;
                } else if ((cadastralValue <= condition_20000000) & (condition_10000000 < cadastralValue)) {
                    return finalBidNoMagic_0_15;
                } else if ((cadastralValue <= condition_50000000) & (condition_20000000 < cadastralValue)) {
                    return finalBid_0_2;
                } else if ((cadastralValue <= condition_300000000) & (condition_50000000 < cadastralValue)) {
                    return finalBid_0_3;
                } else if (cadastralValue > condition_300000000) {
                }
                return finalBid_2;
            }
        },
        MoscowCar { //12
            public double changeFinalBid() {
                System.out.println("MoscowCar");
                return UfaCar_UfaOthers_KazanOthers_MoscowOthers(finalBid_2, finalBid_0_1);
            }
        },
        Gorn { //14
            public double changeFinalBid() {
                System.out.println("Gorn");
                double finalBidNoMagic_0_7 = 0.7;
                return UfaCar_UfaOthers_KazanOthers_MoscowOthers(finalBidNoMagic_0_7, finalBid_0_3);
            }
        };
        public abstract double changeFinalBid();

        private static double UfaRoom_UfaApartment_UfaHouse(double finalBid_00, double finalBid_01, double finalBid_02, double finalBid_03) {
            if (cadastralValue <= condition_4000000) {
                return finalBid_00;
            } else if ((cadastralValue <= condition_7000000) & (condition_4000000 < cadastralValue)) {
                return finalBid_01;
            } else if ((cadastralValue <= condition_10000000) & (condition_7000000 < cadastralValue)) {
                return finalBid_02;
            } else if (cadastralValue > condition_10000000) {
                return finalBid_03;
            } else if (cadastralValue > condition_300000000) {
            }
            return finalBid_2;
        }

        private static double UfaCar_UfaOthers_KazanOthers_MoscowOthers(double finalBid_00, double finalBid_01) {
            if (cadastralValue > condition_300000000) {
                return finalBid_00;
            } else
                return finalBid_01;
        }
    }
}


