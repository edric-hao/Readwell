package ph.edu.dlsu.readwell.ui.books;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BooksViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BooksViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Books Fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}