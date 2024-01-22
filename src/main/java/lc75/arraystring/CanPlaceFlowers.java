package lc75.arraystring;

public class CanPlaceFlowers {

    // 114 / 129 testcases passed
    public boolean canPlaceFlowers1(int[] flowerbed, int n) {
        if(flowerbed.length == 1) {
            if(flowerbed[0] == 0)
                return true;
            else
                return false;
        }

        if(flowerbed.length == 2) {
            if(flowerbed[0] == 0 && flowerbed[1] == 0) {
                if(n == 0)
                    return true;
                else
                    return false;
            }
            else {
                return false;
            }
        }

        for(int i=0;i<flowerbed.length; i++) {
            if(i-1 == -1 && flowerbed[i] == 0 && flowerbed[i+1] == 0) {
                n--;
                flowerbed[i] = 1;
            } else if((i+1) < flowerbed.length && i+1 == flowerbed.length && flowerbed[i] == 0 && flowerbed[i-1] == 0) {
                n--;
                flowerbed[i] = 1;
            }
            else if((i-1) >=0 && (i+1) < flowerbed.length && flowerbed[i-1] == 0 && flowerbed[i] == 0 && flowerbed[i+1] == 0) {                n--;
                flowerbed[i] = 1;

            } else {
                continue;
            }
        }
        return n<=0 ? true : false;
    }

    // Accepted
    // However complicated solution
    // Could be solved easily
    public boolean canPlaceFlowers2(int[] flowerbed, int n) {
        if(flowerbed.length == 1) {
            if(flowerbed[0] == 0 && (n == 1 || n==0))
                return true;
            else if(flowerbed[0] == 1 && n == 0)
                return true;
            else
                return false;
        }
        int start = -1;
        int placement = 0;
        if(flowerbed[0] == 1) {
            start = 0;
        }
        for(int i=1; i<flowerbed.length; i++) {
            if(flowerbed[i] == 1) {
                int result = calcPlacement(start, i, flowerbed.length);
                //System.out.println(result);
                placement += result;
                start = i;
            }
        }
        if(flowerbed[flowerbed.length-1] == 0) {
            //System.out.println("start" + start);
            int result = calcPlacement(start, -1, flowerbed.length);
            //System.out.println(result);
            placement += result;
        }
        //System.out.println(placement);
        return placement >= n ? true : false;
    }

    private int calcPlacement(int start, int end, int size) {
        if(start != -1 && end != -1) {
            int num = (end-start-1);
            return num%2 == 0 ? (num/2) - 1 : (num/2);
        } else if(start == -1 && end != -1) {
            int num = (end-0);
            return num%2 == 0 ? (num/2) : (num/2);
        } else if(start != -1 && end == -1) {
            int num = (size-start-1);
            return num%2 == 0 ? (num/2) : (num/2);
        } else {
            return size%2 == 0 ? (size/2) : (size/2) + 1;
        }

    }

}
