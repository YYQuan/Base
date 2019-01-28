package com.alphawizard.hdwallet.common.base.widget.RecyclerView;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alphawizard.hdwallet.common.R;
import com.alphawizard.hdwallet.common.util.DiffUtilCallback;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;


import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class RecyclerAdapter<Data  extends DiffUtilCallback.DiffRule<Data>, VH extends RecyclerAdapter.BaseHolder> extends BaseQuickAdapter<Data,VH>

{

    private AdapterCallback<VH,Data>    adapterCallback ;

    private LoadMoreView mLoadMoreView = new SimpleLoadMoreView();

    private boolean mEnableLoadMoreEndClick = false;


    public RecyclerAdapter(int layoutResId, @Nullable List<Data> data) {
        super(layoutResId, data);
        init();
    }

    public RecyclerAdapter(@Nullable List<Data> data) {
        super(data);
    }

    public RecyclerAdapter(int layoutResId) {
        super(layoutResId);
        init();
    }

    void init(){

        setLoadMoreView(mLoadMoreView);
    }


    /**
     *
     *  重写这个方法  就是被了 holder 里面能够使用  butter knife  ,但是 可能会出现问题
     */
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {

        VH holder = null;
        View view =  null;
        this.mContext = parent.getContext();
        this.mLayoutInflater = LayoutInflater.from(mContext);

        switch (viewType) {
            case LOADING_VIEW:
                    view = getItemView(mLoadMoreView.getLayoutId(), parent);

                holder =getLoadingView(view);
                break;
            case HEADER_VIEW:
                view = getHeaderLayout();
                holder = createBaseViewHolder(view);
                break;
            case EMPTY_VIEW:
                view = getEmptyView();
                holder = createBaseViewHolder(view);
                break;
            case FOOTER_VIEW:
                view   =getFooterLayout();
                holder = createBaseViewHolder(view);
                break;
            default:
                super.onCreateViewHolder(parent, viewType);
                view = getItemView(mLayoutResId, parent);
                holder = onCreateDefViewHolder(view, viewType);


                break;

        }
//        让 view中绑定了viewHolder的信息 这样view能够得到holder

        if(view !=null) {
            view.setTag(R.id.ViewHolder_Tag, holder);
            holder.unbinder = ButterKnife.bind(holder, view);
        }

        holder.setAdapter(this);
        bindViewClickListener(holder);


        return  (VH)holder;
    }



    private void bindViewClickListener(final BaseViewHolder baseViewHolder) {
        if (baseViewHolder == null) {
            return;
        }
        final View view = baseViewHolder.itemView;
        if (view == null) {
            return;
        }
        if (getOnItemClickListener() != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getOnItemClickListener().onItemClick(RecyclerAdapter.this, v, baseViewHolder.getLayoutPosition() - getHeaderLayoutCount());
                }
            });
        }
        if (getOnItemLongClickListener() != null) {
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return getOnItemLongClickListener().onItemLongClick(RecyclerAdapter.this, v, baseViewHolder.getLayoutPosition() - getHeaderLayoutCount());
                }
            });
        }
    }

    protected VH onCreateDefViewHolder(View view, int viewType) {
//           父类里要  这个 parent 只是为了创建view而已，  由于 view 从外部传进来 ，所以parent 就随便传了。
        super.onCreateDefViewHolder(null, viewType);
        return createBaseViewHolder(view);
    }

    @Override
    protected VH createBaseViewHolder(View view) {
        return super.createBaseViewHolder(view);
    }

    private VH getLoadingView(View view) {

//        View view = getItemView(mLoadMoreView.getLayoutId(), parent);
        VH holder = createBaseViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLoadMoreView.getLoadMoreStatus() == LoadMoreView.STATUS_FAIL) {
                    notifyLoadMoreToLoading();
                }
                if (mEnableLoadMoreEndClick && mLoadMoreView.getLoadMoreStatus() == LoadMoreView.STATUS_END) {
                    notifyLoadMoreToLoading();
                }
            }
        });
        return holder;
    }

    /**
     * Load more without data when settings are clicked loaded
     *
     * @param enable
     */
    public void enableLoadMoreEndClick(boolean enable) {
        mEnableLoadMoreEndClick = enable;
    }

    public void setLoadMoreView(LoadMoreView mLoadMoreView) {
        this.mLoadMoreView = mLoadMoreView;
        super.setLoadMoreView(mLoadMoreView);
    }

    @Override
    protected void convert(VH holder, Data item) {

        holderConvert( holder,item);
        onRecyclerChange(holder,item);

    }

    protected  abstract void  holderConvert(VH helper, Data item);

    public    void  onRecyclerChange(VH  holder,Data  data){
        if(adapterCallback!=null){
            adapterCallback.update(holder,data);
        }
    }

    public void setAdapterCallback(AdapterCallback<VH ,Data> adapterCallback) {
        this.adapterCallback = adapterCallback;
    }


    /**
     * 一定要注意
     * 用这种方式刷新时 ，  参数对象不要是 当前的adapter 引用的data 对象
     * 要不对导致数组越界崩溃
     * @param list
     */
    public void refreshData(  List<Data> list){

        if(list!=null&&list.size()>0) {
            // 进行数据对比
            DiffUtil.Callback callback = new DiffUtilCallback<>(getData(), list);
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
            getData().clear();
            getData().addAll(list);
            result.dispatchUpdatesTo(this);
        }
    }


    /**
     *      如果要在  子类当中 使用  butterknife  ，
     *     控件 上除了  bindview  注解之外   还必须 加入
     *      @Nullable
     *      注解，
     *      因为holder 不只要我们的目标holder 还有 load  foot  ,header 等holder
     *      所以说  控件是很有可能为空的。
      */
    public class BaseHolder extends BaseViewHolder {
        Unbinder unbinder;
        public BaseHolder(View view) {
            super(view);
        }

        @Override
        protected BaseViewHolder setAdapter(BaseQuickAdapter adapter) {
            return super.setAdapter(adapter);
        }
    }



}
