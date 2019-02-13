# Base
一个android 基类框架 里面使用了 rxjava + dagger +life cycle + aop 实现了一个类似 MVVM的模式的基本框架

该框架的优势：  功能之间的非常解耦，复用性很高，逻辑清晰。而且功能部分复用起来很方便。

整体的结构是
    由support目录  下来提供全部所需要的功能，然后把 support 中的功能分离开了 构成  Interact。一个一个的interact提供
  activity/fragment 所属的viewmodel, activity中就通过 viewModel来执行所需要的功能。interact可以功能的最小单位，
  这样interact的复用性就大大的提高了。
    再通过lifecycle的配合，达到如下效果
    
    void onCreate(){
      ...
      // viewModel 里通过interact的支持 有  createWallet的功能，
      // 这时通过lifecycle来监听createWallet是否被执行，如果被执行了，则执行onCreateWallet 这个函数
      viewModel.observeCreatedWallet().observe(this,this::onCreatedWallet);
    }
    
    @OnClick(R.id.xxx)
    public  void click(){
      viewModel.createWallet();
    }
    
    public void onCreatedWallet(Wallet wallet) {
        MyLogger.jLog().d("onCreatedWallet");
        viewModel.openWallet(this);
    }
    
    这样activity中对于各个功能的回调监听就显得简洁很多。
    
    在加入一个activity至少要有四个部分：
      1.activity本身
      2.该activity的viewmodle(用于给activity提供各种功能)
      3.该activity的router(用于跳转)
      4.提供给activity对象的 dagger module(不一定需要)
    
    在这种模式下， activity 的视图部分和功能部分就是比较解耦的了。

继承面向切面
    使用aspectJ实现了 用注解来完成 异步，权限申请， 防止重复点击，将返回值保存在数据库或者sharePreference当中等等功能
    使得开发更加快捷



封装recyclerView
    在开源项目 BaseRecyclerViewAdapterHelper的基础上我做了二次封装，
    使得在使用在 只要对RecyclerAdapter  进行继承，给recycler 的bean实现DiffUtilCallback接口，
    就可以直接使用RecyclerAdapter.refreshData 来实现 比较后更新
    
    
    

      
    
    
    
