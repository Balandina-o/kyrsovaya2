package enums;

import java.util.Arrays;

/**
 * Класс, отвечающий за определение налоговой ставки, используемой в расчетах, в зависимости от выбранных
 * пользователем элементов выпадающего списка, позволяющего выбрать муниципальное образование и размера кадастровой стоимости
 * объекта недвижимости
 *
 * @author Artyom
 * @version 2.0
 */
public class EnumSwitch {
    public static final double finalBidChange1 = 0.11;
    public static final double finalBidChange2 = 0.11;
    public static final double finalBidChange3 = 0.11;
    public static final double finalBidChange4 = 0.11;
    public static final double finalBidChange5 = 0.11;
    // Объявление числовой переменной, хранящей значение налоговой ставки
    public static double finalbid;

    // Объявление строковых переменных, хранящих их выбранные элементы выпадающих списков
    public static String propertyIndex, regionIndex;

    // Объявление переменной для хранения значения кадастровой стоимости объекта налогообложения
    public static double cadastralValue;

    /**
     * Метод-установщик значения строковой переменной, являющейся выбранным элементом выпадающего списка,
     * позволяющего выбрать тип налогооблагаемого объекта.
     *
     * @param propertyIndex - строковая переменная, являющаяся выбранным элементом выпадающего списка,
     *                      позволяющего выбрать тип налогооблагаемого объекта
     */
    public void setPropertyIndex(String propertyIndex) {
        this.propertyIndex = propertyIndex;
    }

    /**
     * Метод-установщик значения строковой переменной, являющейся выбранным элементом выпадающего списка,
     * позволяющего выбрать муниципальное образование.
     *
     * @param regionIndex - строковая переменная, являющейся выбранным элементом выпадающего списка,
     *                    позволяющего выбрать муниципальное образование
     */
    public void setRegionIndex(String regionIndex) {
        this.regionIndex = regionIndex;
    }

    /**
     * Метод-установщик значения числовой переменной, в которую помещается кадастровая стоимость
     * объекта налогообложения
     *
     * @param cadastralValue - кадастровая стоимость объекта налогообложения
     */
    public void setCadastralValue(double cadastralValue) {
        this.cadastralValue = cadastralValue;
    }

    /**
     * Метод, возвращающий значение налоговой ставки
     *
     * @return the final bid - значение налоговой ставки
     */
    public double getFinalbid() {
        return finalbid;
    }

    /**
     * Метод для инициализации налоговой ставки в зависимости от выбранного города и имущества.
     * Сначала реализуется поиск элемента, удовлетворяющего 2‑м выбранным пунктам combobox
     * Затем происходит создание экземпляра интерфейса Selection.
     * В зависимости от найденного элемента присваивается значение перечисление в переменную.
     * Затем у этого значения перечисления вызывается метод Check который устанавливает значение налоговой ставки.
     */
    public static void enumuse() {
        String[] gggg = {"00", "01", "02", "03", "04", "10", "11", "12", "13", "14", "20", "21", "22", "23", "24", "30", "31", "32", "33", "34"};
        String[] xxxx = {propertyIndex, regionIndex};
        String yyyy = xxxx[0] + xxxx[1];

        int x = Arrays.binarySearch(gggg, yyyy);
        if ((15 <= x) & (x <= 19)) {
            Selection day = cityproperty.values()[15];
            finalbid = day.Check();
        } else {
            Selection day = cityproperty.values()[x];
            finalbid = day.Check();
        }
    }

    /**
     * Вложенный класс перечислений cityproperty, реализовывающий интерфейс Selection
     */
    enum cityproperty implements Selection {
        /**
         * элемент перечисления  UfaRoom переопределяющий метод Check интерфейса Selection
         */
        UfaRoom() {
            @Override
            public double Check() {
                //FIXME 100000000 ---> 10000000 ИЗМЕНИЛ НА ОДИН 0
                return UfaRoom_UfaApartment();
            }
        },
        /**
         * элемент перечисления  UfaApartment переопределяющий метод Check интерфейса Selection
         */
        UfaApartment {
            @Override
            public double Check() {
                return UfaRoom_UfaApartment();
            }
        },
        /**
         * элемент перечисления  UfaHouse переопределяющий метод Check интерфейса Selection
         */
        UfaHouse {
            @Override
            public double Check() {
                if (cadastralValue <= 4000000) {
                    return 0.12;
                } else if ((cadastralValue <= 7000000) & (4000000 < cadastralValue)) {
                    return 0.18;
                } else if ((cadastralValue <= 10000000) & (7000000 < cadastralValue)) {
                    return 0.216;
                } else if (cadastralValue > 10000000) {
                    return 0.24;
                } else if (cadastralValue > 300000000) {
                }
                return 2;
            }
        },
        /**
         * элемент перечисления  UfaCar переопределяющий метод Check интерфейса Selection
         */
        UfaCar {
            @Override
            public double Check() {
                if (cadastralValue > 300000000) {
                    return 2;
                } else
                    return 0.11;
            }
        },

        /**
         * элемент перечисления  UfaOthers переопределяющий метод Check интерфейса Selection
         */
        UfaOthers {
            @Override
            public double Check() {
                return KazanOthers_MoscowOthers_UfaOthers();
            }
        },

        /**
         * элемент перечисления  KazanRoom переопределяющий метод Check интерфейса Selection
         */
        KazanRoom {
            @Override
            public double Check() {
                return KazanRoom_KazanApartment();
            }
        },

        /**
         * элемент перечисления  KazanApartment переопределяющий метод Check интерфейса Selection
         */
        KazanApartment {
            @Override
            public double Check() {
                return KazanRoom_KazanApartment();
            }
        },

        /**
         * элемент перечисления  KazanHouse переопределяющий метод Check интерфейса Selection
         */
        //TODO ИЗМЕНЕН
        KazanHouse {
            @Override
            public double Check() {
                return KazanHouse_KazanCar();
            }
        },

        /**
         * элемент перечисления  KazanCar переопределяющий метод Check интерфейса Selection
         */
        //TODO ИЗМЕНЕН
        KazanCar {
            @Override
            public double Check() {
                return KazanHouse_KazanCar();
            }
        },

        /**
         * элемент перечисления  KazanOthers переопределяющий метод Check интерфейса Selection
         */
        KazanOthers {
            @Override
            public double Check() {
                return KazanOthers_MoscowOthers_UfaOthers();
            }
        },

        /**
         * элемент перечисления  MoscowRoom переопределяющий метод Check интерфейса Selection
         */
        MoscowRoom {
            @Override
            public double Check() {
                return MoscowRoom_MoscowApartment_MoscowHouse();
            }
        },

        /**
         * элемент перечисления  MoscowApartment переопределяющий метод Check интерфейса Selection
         */
        MoscowApartment {
            @Override
            public double Check() {
                return MoscowRoom_MoscowApartment_MoscowHouse();
            }
        },

        /**
         * элемент перечисления  MoscowHouse переопределяющий метод Check интерфейса Selection
         */
        MoscowHouse {
            @Override
            public double Check() {
                return MoscowRoom_MoscowApartment_MoscowHouse();
            }
        },

        /**
         * элемент перечисления  MoscowCar переопределяющий метод Check интерфейса Selection
         */
        MoscowCar {
            @Override
            public double Check() {
                if (cadastralValue > 300000000) {
                    return 2;
                } else
                    return 0.1;
            }
        },

        /**
         * элемент перечисления  MoscowOthers переопределяющий метод Check интерфейса Selection
         */
        MoscowOthers {
            @Override
            public double Check() {
                return KazanOthers_MoscowOthers_UfaOthers();
            }
        },

        /**
         * элемент перечисления  Gorn переопределяющий метод Check интерфейса Selection
         */
        Gorn {
            @Override
            public double Check() {
                if (cadastralValue > 300000000) {
                    return 0.7;
                } else
                    return 0.3;
            }
        };

        private static double KazanHouse_KazanCar() {
            if (cadastralValue > 300000000) {
                return 2;
            } else
                return 0.3;
        }

        private static double KazanOthers_MoscowOthers_UfaOthers() {
            if (cadastralValue > 300000000) {
                return 2;
            } else
                return 0.5;
        }

        private static double MoscowRoom_MoscowApartment_MoscowHouse() {
            if (cadastralValue <= 10000000) {
                return 0.1;
            } else if ((cadastralValue <= 20000000) & (10000000 < cadastralValue)) {
                return 0.15;
            } else if ((cadastralValue <= 50000000) & (20000000 < cadastralValue)) {
                return 0.2;
            } else if ((cadastralValue <= 300000000) & (50000000 < cadastralValue)) {
                return 0.3;
            } else if (cadastralValue > 300000000) {
            }
            return 2;
        }

        private static double KazanRoom_KazanApartment() {
            if (cadastralValue > 300000000) {
                return 2;
            } else
                return 0.2;
        }

        private static double UfaRoom_UfaApartment() {
            if (cadastralValue <= 4000000) {
                return 0.11;
            } else if ((cadastralValue <= 7000000) & (4000000 < cadastralValue)) {
                return 0.165;
            } else if ((cadastralValue <= 10000000) & (7000000 < cadastralValue)) {
                return 0.198;
            } else if (cadastralValue > 10000000) {
                return 0.22;
                //TODO что это
            } else if (cadastralValue > 300000000) {
            }
            return 2;
        }
    }
}


