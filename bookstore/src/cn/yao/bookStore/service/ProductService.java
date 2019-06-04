package cn.yao.bookStore.service;

import java.sql.SQLException;
import java.util.List;

import cn.yao.bookStore.dao.ProductDao;
import cn.yao.bookStore.domain.Order;
import cn.yao.bookStore.domain.PageBean;
import cn.yao.bookStore.domain.Product;
import cn.yao.bookStore.exception.AddProductException;
import cn.yao.bookStore.exception.FindProductByIdException;
import cn.yao.bookStore.exception.ListProductException;

public class ProductService {
	private ProductDao dao = new ProductDao();
	
	// 分页操作
		public PageBean findProductByPage(int currentPage, int currentCount, String category) {
			PageBean bean = new PageBean();
			// 封装每页显示数据条数
			bean.setCurrentCount(currentCount);
			// 封装当前页码
			bean.setCurrentPage(currentPage);

			// 封装当前查找类别
			bean.setCategory(category);

			try {
				// 获取总条数
				int totalCount = dao.findAllCount(category);
				bean.setTotalCount(totalCount);

				// 获取总页数
				int totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
				bean.setTotalPage(totalPage);

				// 获取当前页数据
				List<Product> psList = dao.findByPage(currentPage, currentCount, category);
				bean.setPsList(psList);

			} catch (SQLException e) {
				e.printStackTrace();
			}

			return bean;
		}
	
	// 查找所有商品信息
		public List<Product> listAll() throws ListProductException {
			try {
				return dao.listAll();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new ListProductException("查询商品失败");
			}
		}
	
	// 根据id查找商品
		public Product findProductById(String id) throws FindProductByIdException {
			try {
				return dao.findProductById(id);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new FindProductByIdException("根据ID查找商品失败");
			}
		}
	
	// 添加商品
		public void addProduct(Product p) throws AddProductException {

			try {
				dao.addProduct(p);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new AddProductException("添加商品失败");
			}
		}
		
	//模糊查询
		public PageBean findBookByName(int currentPage, int currentCount,
				String searchfield) {
			PageBean bean = new PageBean();
			// 封装每页显示数据条数
			bean.setCurrentCount(currentCount);
			System.out.println("mohu currenCount"+currentCount);
			// 封装当前页码
			bean.setCurrentPage(currentPage);
			System.out.println("mohu currenPage"+currentPage);
			// 封装模糊查询的图书名
			bean.setSearchfield(searchfield);
			System.out.println("ps search"+searchfield);
			try {
				// 获取总条数
				int totalCount = dao.findBookByNameAllCount(searchfield);
				System.out.println("pd参数的总条数："+totalCount);
				bean.setTotalCount(totalCount);

				// 获取总页数
				int totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
				System.out.println("pd参数的总数："+totalPage);
				bean.setTotalPage(totalPage);

				//满足条件的图书
				List<Product> ps = dao.findBookByName(currentPage,currentCount,searchfield);
				bean.setPsList(ps);
				System.out.println("ps"+bean);
				return bean;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("前台搜索框根据书名查询图书失败！");
			}
		}
		//修改产品剩余数量
		public void changeProductNum(Order order) throws SQLException {
			dao.changeProductNum(order);
		}
		

}
