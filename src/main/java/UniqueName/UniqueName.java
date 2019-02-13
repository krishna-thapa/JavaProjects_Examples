package UniqueName;

public class UniqueName {
    public static String firstUniqueName(String[] names) {
        //throw new UnsupportedOperationException("Waiting to be implemented.");
        if(names.length == 0){
            return null;
        }
        int n = names.length;
        for (int i = 0; i < n; i++) {
            int j;
            for (j = 0; j < n; j++)
                if (i != j && names[i] == names[j]) {
                    System.out.println(i+"---"+j+"----"+names[j]);
                    break;
                }
            if (j == n){
                System.out.println(n+"---"+j+"----"+names[i]);
                return names[i];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(firstUniqueName(new String[] { "Abbi", "Adeline", "Abbi", "Adalia","Adeline" }));
    }
}
