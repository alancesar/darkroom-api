package org.alancesar.darkroom.engine.dictionary;

public abstract class ItemIFD0DirectoryOrSubIFDDirectory  implements Item<String> {

    protected Item<String> next;
    
    @Override
    public String read(Dictionary dictionary) {
        
        String value = dictionary.getExifIFD0().getDescription(ifd0());

        if ((value == null || value.isEmpty())) {
            value = dictionary.getExifSubIFD().getDescription(subIfd());
            
            if (value == null || value.isEmpty()) {
                return next.read(dictionary);
            }
        }

        return value;
    }

    @Override
    public void setNext(Item<String> next) {
        this.next = next;
    }
    
    protected abstract int ifd0();
    protected abstract int subIfd();
}
