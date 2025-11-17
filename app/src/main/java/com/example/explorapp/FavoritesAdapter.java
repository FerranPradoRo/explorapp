package com.example.explorapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.explorapp.models.Favorite;

import java.util.ArrayList;
import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder> {

    private List<Favorite> favorites;
    private OnFavoriteClickListener listener;

    public interface OnFavoriteClickListener {
        void onFavoriteClick(Favorite favorite);
        void onRemoveClick(Favorite favorite, int position);
    }

    public FavoritesAdapter(OnFavoriteClickListener listener) {
        this.favorites = new ArrayList<>();
        this.listener = listener;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
        notifyDataSetChanged();
    }

    public void removeFavorite(int position) {
        favorites.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorite, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        Favorite favorite = favorites.get(position);
        holder.bind(favorite, listener);
    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

    static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private TextView tvPlaceName;
        private TextView tvCategory;
        private TextView tvCity;
        private ImageView btnRemove;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPlaceName = itemView.findViewById(R.id.tv_place_name);
            tvCategory = itemView.findViewById(R.id.tv_category);
            tvCity = itemView.findViewById(R.id.tv_city);
            btnRemove = itemView.findViewById(R.id.btn_remove);
        }

        public void bind(Favorite favorite, OnFavoriteClickListener listener) {
            if (favorite.getLocation() != null) {
                tvPlaceName.setText(favorite.getLocation().getName());
                tvCategory.setText(favorite.getLocation().getCategoryName() != null
                        ? favorite.getLocation().getCategoryName()
                        : "Sin categoría");
                tvCity.setText(favorite.getLocation().getCity());

                // Click en la card para ver detalles
                itemView.setOnClickListener(v -> {
                    if (listener != null) {
                        listener.onFavoriteClick(favorite);
                    }
                });

                // Click en el botón de eliminar
                btnRemove.setOnClickListener(v -> {
                    if (listener != null) {
                        listener.onRemoveClick(favorite, getAdapterPosition());
                    }
                });
            }
        }
    }
}
