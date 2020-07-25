/**
 * 系统配置模块
 */ 

 // save
 export function save() { 
     return {
         url:'config/save',
         type:'post',
         data:{
             "code":200,
             "msg":null,
             "data":1
         }
     }
  }
//   批量删除
export function batchDelete(){
    return {
        url:'config/delete',
        type:'post',
        data:{
            "code":200,
            "msg":null,
            "data":1
        }
    }
}