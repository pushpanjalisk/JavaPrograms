class countNumberOFWordsINString {
    public static void main(String[] args) {
        String name = "Hero Vired - Full Stack Development Program";
        int wordsCount = name.split("\\s").length;
        System.out.println(wordsCount);
    }
}