package example.danielsierraf.pruebarappi.apps;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.easyandroidanimations.library.FoldAnimation;
import com.easyandroidanimations.library.FoldLayout.Orientation;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.danielsierraf.pruebarappi.R;
import example.danielsierraf.pruebarappi.api.classes.AppDetail;

/**
 * Created by danielsierraf on 6/10/16.
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private Context mContext;
    private List<AppDetail> entry;

    public DataAdapter(List<AppDetail> entry, Context context){
        this.entry = entry;
        mContext = context;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        AppDetail appDetail = entry.get(position);
        holder.name.setText(appDetail.getImName().getLabel());
        holder.title.setText(appDetail.getTitle().getLabel());
        holder.id = appDetail.getId().getAttributes().getImId();
        Glide.with(mContext).load(appDetail.getImImage().get(0).getLabel())
                .override(240, 240)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return entry.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        String id;
        @BindView(R.id.app_name) TextView name;
        @BindView(R.id.app_summary) TextView title;
        @BindView(R.id.app_photo) ImageView image;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public TextView getName() {
            return name;
        }

        public TextView getTitle() {
            return title;
        }

        public ImageView getImage() {
            return image;
        }

        public String getId() {
            return id;
        }
    }
}
