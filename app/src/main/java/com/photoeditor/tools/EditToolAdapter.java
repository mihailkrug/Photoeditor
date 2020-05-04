package com.photoeditor.tools;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.photoeditor.R;

import java.util.ArrayList;
import java.util.List;

public class EditToolAdapter extends RecyclerView.Adapter<EditToolAdapter.ViewHolder> {

    private List<myToolModel> toolList = new ArrayList<>();
    private OnItemSelected itemSelect;

    public EditToolAdapter(OnItemSelected onItemSelected) {
        itemSelect = onItemSelected;
        toolList.add(new myToolModel("Brush", R.drawable.ic_brush, ToolType.BRUSH));
        toolList.add(new myToolModel("Text", R.drawable.ic_text, ToolType.TEXT));
        toolList.add(new myToolModel("Eraser", R.drawable.ic_eraser, ToolType.ERASER));
        toolList.add(new myToolModel("Filter", R.drawable.ic_photo_filter, ToolType.FILTER));
        toolList.add(new myToolModel("Emoji", R.drawable.ic_insert_emoticon, ToolType.EMOJI));
        toolList.add(new myToolModel("Sticker", R.drawable.ic_sticker, ToolType.STICKER));
    }

    public interface OnItemSelected {
        void onToolSelected(ToolType toolType);
    }

    class myToolModel {
        private String mToolName;
        private int mToolIcon;
        private ToolType mToolType;

        myToolModel(String toolName, int toolIcon, ToolType toolType) {
            mToolName = toolName;
            mToolIcon = toolIcon;
            mToolType = toolType;
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_editing_tools, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        myToolModel item = toolList.get(position);
        holder.txtTool.setText(item.mToolName);
        holder.imgToolIcon.setImageResource(item.mToolIcon);
    }

    @Override
    public int getItemCount() {
        return toolList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgToolIcon;
        TextView txtTool;

        ViewHolder(View itemView) {
            super(itemView);
            imgToolIcon = itemView.findViewById(R.id.imgToolIcon);
            txtTool = itemView.findViewById(R.id.txtTool);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemSelect.onToolSelected(toolList.get(getLayoutPosition()).mToolType);
                }
            });
        }
    }
}
