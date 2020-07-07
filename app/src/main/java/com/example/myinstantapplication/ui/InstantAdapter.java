package com.example.myinstantapplication.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myinstantapplication.R;
import com.example.myinstantapplication.bean.TranslateBean;
import com.example.myinstantapplication.bean.TranslateSpeekUser;
import java.util.List;

/**
 * Description : InstantAdapter
 *
 * @author zimin
 * @date 2019/8/27
 */

public class InstantAdapter extends ArrayAdapter<TranslateBean> {
	private int resource_id;
	public InstantAdapter(Context context,  int resource,  List<TranslateBean> objects) {
		super(context, resource, objects);
		resource_id=resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TranslateBean msg = getItem(position);
		/*
		 * 新增内部类ViewHolder，用于对控件的实例进行缓存。
		 * 1.当convertView为空时，创建一个ViewHolder对象，并将控件的实例对象存放在ViewHolder里。
		 * 2.然后调用View的setTag()方法，将ViewHolder对象存储在View中。
		 * 3.当convertView不为空时，调用View的getTag()方法把ViewHolder重新取出来。
		 * */
			View view;
			ViewHolder viewHolder;
			/*加载自定义布局与控件实例*/
		if(convertView == null){
			view = LayoutInflater.from(getContext()).inflate(resource_id, null);
			//创建控件实例并进行缓存
			viewHolder = new ViewHolder();
			viewHolder.leftLayout = (LinearLayout) view.findViewById(R.id.left_layout);
			viewHolder.rightLayout = (LinearLayout) view.findViewById(R.id.right_layout);
			viewHolder.leftMsg = (TextView) view.findViewById(R.id.left_msg);
			viewHolder.rightMsg = (TextView) view.findViewById(R.id.right_msg);
			viewHolder.left_msg_before=(TextView)view.findViewById(R.id.left_msg_before);
			viewHolder.right_msg_before=(TextView)view.findViewById(R.id.right_msg_before);
			view.setTag(viewHolder);

		} else {
			//convertView参数用于将之前加载好的布局进行缓存，以便之后可以进行复用（提高效率）
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
			/*接受与发送消息的分类处理*/
		//如果为收到的消息，则显示左边的消息布局，将右边的消息布局隐藏
			if(TranslateSpeekUser.LEFT_TRANSLATE_SPEEK.equals(msg.getTranslateSpeekUser())){
			viewHolder.leftLayout.setVisibility(View.VISIBLE);
			viewHolder.rightLayout.setVisibility(View.GONE);
			viewHolder.leftMsg.setText(msg.getTranslateInContent());
			viewHolder.left_msg_before.setText(msg.getTranslateOutContent());

		} else if(TranslateSpeekUser.RIGHT_TRANSLATE_SPEEK.equals(msg.getTranslateSpeekUser())){
			viewHolder.rightLayout.setVisibility(View.VISIBLE);
			viewHolder.leftLayout.setVisibility(View.GONE);
			viewHolder.rightMsg.setText(msg.getTranslateInContent());
			viewHolder.right_msg_before.setText(msg.getTranslateOutContent());
		}
			return view;
	}

	//新增内部类ViewHolder，用于对控件的实例进行缓存。
	class ViewHolder{
		LinearLayout leftLayout;
		LinearLayout rightLayout;
		TextView leftMsg;
		TextView rightMsg;
		TextView left_msg_before;
		TextView right_msg_before;
	}
}
