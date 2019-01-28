package ru.tsystems.pyramidBuilder;

public class PyramidBuilderImpl implements PyramidBuilder {
    @Override
    public int[][] build(int[] list) {
        // TODO: Постройка пирамиды
        int quantity = list.length;
        int height = 0;
        int width = 0;
        int[] source = list;
        int[][] pyramid;

        try {
            height = getHeight(list);
        } catch (CannotBuildPyramidException e) {
            System.out.println("из этого невозможно построить пирамиду");
            e.printStackTrace();
        }

        width = 2 * height - 1;

        quickSort(source, 0, source.length-1);

        int cage = 0;

        pyramid = new int[height][width];

        for(int i = 0; i < height; i++ ){

            boolean flag = true;//флаг, для того чтобы каждый четный раз срабатывала подстановка

            for(int j = 0; j < width; j++){

                pyramid [i][j] = 0;

                int indent = (width - (i + 1)*2 - 1)/2;//высчитываем отступ для кажого этажа пирамиды

                if((j > indent )&&(j < width - indent)){//если мы находимся между отступами, мы вставляем числа

                    if(flag){
                        pyramid[i][j] = source[cage];
                        cage++;
                        flag = false;
                    }else{
                        flag = true;
                    }

                }


            }


        }

        return pyramid;
    }

    //проверка списка на возможность быть превращенным в пирамиду
    //на выходе  выдаст либо высоту пирамиды либо кинет exception
    public int getHeight(int[] list) throws CannotBuildPyramidException {
        // TODO: Проверка, получится ли пирамида
        int quantity = list.length;
        int height = 0;


        while (quantity > 0) {
            height++;
            quantity -= height;
        }

        if (quantity == 0) {
            return height;
        } else {
            throw new CannotBuildPyramidException();
        }

    }

   public class CannotBuildPyramidException extends Exception {
        // TODO: Создание кастомного исключения
    }



    public void quickSort(int[] list, int start, int end) {
        // TODO: Сортировка значний в массиве


        if (list == null || list.length == 0)
            throw new IllegalArgumentException();

        if (start >= end)
            throw new IllegalArgumentException();

        //определяем опорный элемент
        int middle = start + (end - start) / 2;
        int pivot = list[middle];

        // слева меньше опорного справа больше опорного
        int i = start, j = end;
        while (i <= j) {
            while (list[i] < pivot) {
                i++;
            }

            while (list[j] > pivot) {
                j--;
            }

            if (i <= j) {
                int temp = list[i];
                list[i] = list[j];
                list[j] = temp;
                i++;
                j--;
            }
        }

        // выполняем для каждой из половин
        if (start < j)
            quickSort(list, start, j);

        if (end > i)
            quickSort(list, i, end);

    }
}