package index1.developer.acadview.com.dictionaryapp;


public class DictObjectModel {

        String word, meaning;

        public DictObjectModel(String word, String meaning){

            this.word=word;
            this.meaning = meaning;


        }
        public  String getWord()
        {
            return word;
        }

        public  String getMeaning()
        {
            return meaning;
        }

}
