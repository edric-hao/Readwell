package ph.edu.dlsu.readwell.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;

import java.util.List;

import ph.edu.dlsu.readwell.R;

public class HomeFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView genre = root.findViewById(R.id.book_genre);
        final TextView title = root.findViewById(R.id.book_title);
        final TextView author = root.findViewById(R.id.book_author);
        final TextView rating = root.findViewById(R.id.book_rating);
        //final ImageView img = root.findViewById(R.id.book_image);
        Python py = Python.getInstance();
        final PyObject pyobj = py.getModule("main");
        List<PyObject> obj = pyobj.callAttr("best").asList();
        genre.setText(obj.get(3).toString());
        title.setText(obj.get(0).toString());
        author.setText(obj.get(1).toString());
        rating.setText(obj.get(2).toString());
        new DownloadImageTask((ImageView) root.findViewById(R.id.book_image)).execute(obj.get(4).toString());
        return root;
    }
}