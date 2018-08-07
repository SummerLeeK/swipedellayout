package com.example.administrator.myapplication.recycler.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean.NormalBean;
import com.example.administrator.myapplication.recycler.viewholder.BaseViewHolder;

/**
 * Created by Administrator on 2018/7/5 0005.
 */

public class NormalAdapter extends BaseRecyclerAdapter<NormalBean, BaseViewHolder> {

    //---京东和淘宝的商铺及商品ID
    private String TaoBaoShopId = "131259851";   //--耐凡眼镜店
    private String JDShopId = "1000004123";     //--京东小米官方旗舰店
    private String TaoBaoGoodsId = "525249416835";  //--时尚潮流复古学生...眼镜框
    private String JDGoodsId = "4099139";       //--小米6详情页

    //--1.打开京东或淘宝的店铺
    private String taobaoAppStr_shop = "taobao://shop.m.taobao.com/shop/shop_index.htm?shop_id=" + TaoBaoShopId + "";
    private String taobaoWebStr_shop = "https://shop.m.taobao.com/shop/shop_index.htm?shop_id=" + TaoBaoShopId + "";
    private String jdAppStr_shop = "openApp.jdMobile://virtual?params={\"category\":\"jump\",\"des\":\"jshopMain\",\"shopId\":\"" + JDShopId + "\",\"sourceType\":\"M_sourceFrom\",\"sourceValue\":\"dp\"}";
    private String jdWebStr_shop = "http://shop.m.jd.com/?shopId=" + JDShopId + "";

    //--2.打开京东或淘宝的商品详情页
    private String taobaoAppStr_goods = "taobao://item.taobao.com/item.htm?id=" + TaoBaoGoodsId + "";
    private String taobaoWebStr_goods = "https://item.taobao.com/item.htm?id=" + TaoBaoGoodsId + "";
    private String jdAppStr_goods = "openApp.jdMobile://virtual?params={\"category\":\"jump\",\"des\":\"productDetail\",\"skuId\":\"" + JDGoodsId + "\",\"sourceType\":\"JSHOP_SOURCE_TYPE\",\"sourceValue\":\"JSHOP_SOURCE_VALUE\"}";
    private String jdWebStr_goods = "https://item.m.jd.com/product/" + JDGoodsId + ".html";

    public NormalAdapter(int layout) {
        super(layout);
    }

    @Override
    public void convert(final BaseViewHolder holder, NormalBean item) {


        holder.setItemViewClicklistener(R.id.tv1, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.setData(Uri.parse("swipedelmenulayout://swipe?aa=1&bb=2"));
                v.getContext().startActivity(intent);
            }
        });

        holder.setItemViewClicklistener(R.id.tv2, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setData(Uri.parse(jdAppStr_goods));
                v.getContext().startActivity(intent);
            }
        });
        holder.setItemViewClicklistener(R.id.tv3, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setData(Uri.parse(taobaoAppStr_goods));
                v.getContext().startActivity(intent);
            }
        });


    }
}
