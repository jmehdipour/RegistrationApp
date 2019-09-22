package ir.immortalapps.registrationapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.List;

import ir.immortalapps.registrationapp.data.db.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> users;

    public UserAdapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.bindUser(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTv;
        private TextView emailTv;
        private TextView countryTv;
        private TextView registerDateTv;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.tv_user_name);
            emailTv = itemView.findViewById(R.id.tv_user_email);
            countryTv = itemView.findViewById(R.id.tv_user_country);
            registerDateTv = itemView.findViewById(R.id.tv_user_registerDate);
        }

        public void bindUser(User user){
            nameTv.setText(user.getName());
            emailTv.setText(user.getEmail());
            countryTv.setText(user.getCountry());
            registerDateTv.setText(new Date(user.getRegistrationDate()).toString());
        }
    }

}
