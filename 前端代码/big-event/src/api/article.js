import request from '@/utils/request.js'
import {useTokenStore} from '@/stores/token.js'


//文章分类列表查询
export const articleCategoryListSerch = ()=>{

    //const tokenStore = useTokenStore()
    //在请求头中携带token
    //return request.get('/category',{headers:{'Authorization':tokenStore.token}})
    return request.get('/category')
}

//添加文章分类
export const addArticleCategory = (categoryData)=>{
    return request.post('/category',categoryData)
}

//修改文章分类
export const editArticleCategory = (categoryData)=>{
    return request.put('/category',categoryData)
}

//删除文章分类
export const deleteArticleCategory = (id)=>{
    return request.delete('/category?id='+id)
}

