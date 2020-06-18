package com.example.keepaccounts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<KeepAccountBean> mDatas;


    public MyAdapter(Context context, List<KeepAccountBean> datas) {

        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_keep_account, parent, false);
            holder = new ViewHolder();
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            holder.tv_type = (TextView) convertView.findViewById(R.id.tv_type);
            holder.tv_money = (TextView) convertView.findViewById(R.id.tv_money);
            holder.tv_location = (TextView) convertView.findViewById(R.id.tv_location);
            holder.tv_cost_type = (TextView) convertView.findViewById(R.id.tv_cost_type);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        KeepAccountBean keepAccountBean = mDatas.get(position);
        holder.tv_location.setText(keepAccountBean.getLocation());
        holder.tv_type.setText(keepAccountBean.getType()==1?"支出":"收入");
        holder.tv_time.setText(keepAccountBean.getCreateTime());
        holder.tv_money.setText(keepAccountBean.getMoney() + "");
        holder.tv_cost_type.setText(keepAccountBean.getCostType() + "");
        return convertView;
    }


    private class ViewHolder {
        TextView tv_time;
        TextView tv_type;
        TextView tv_money;
        TextView tv_location;
        TextView tv_cost_type;
    }
}
