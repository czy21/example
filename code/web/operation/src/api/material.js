import request from './request';

export default {

  // 根据条件获取分页商品列表
  getPageMaterials: (page) => {
    return request.post('material/getPageMaterials', page);
  },

// 根据商品类别获取商品列表
  getCategoryMaterials: (categoryId) => {

    return request.get('material/getCategoryMaterials', {categoryId: categoryId});
  },

// 根据仓库获取商品列表
  getDepotMaterials: (depotId) => {
    return request.get('material/getDepotMaterials', {depotId: depotId});
  },

// 根据商品获取仓库列表
  getMaterialDepots: (materialId) => {

    return request.get('material/getMaterialDepots', {materialId: materialId});
  },

// 为商品分配仓库
  saveMaterialDepots: (materialId, materialDepots) => {
    return request.post('/material/saveMaterialDepots', {materialId: materialId, materialDepots: materialDepots});
  },

// 添加商品
  create: (data) => {
    return request.post('material/create', data);
  },

// 修改仓库
  edit: (data) => {
    return request.put('material/edit', data);
  },

// 删除仓库
  del: (id) => {
    return request.delete('material/delete', {id: id});
  }
}



