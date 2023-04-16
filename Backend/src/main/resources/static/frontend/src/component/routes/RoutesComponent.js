import { Route, Routes } from "react-router";
import IndexPage from "../index-page/IndexPage";
import CategoriesComponent from "../categories-page/CategoriesComponent";
import BasketPage from "../basket-page/BasketPage";
import ProductsComponent from "../categories-page/ProductsComponent";

const RoutesComponent = () => {
  return (
    <div>
      <Routes>
        <Route index element={<IndexPage />}></Route>
        <Route path="/index" element={<IndexPage />}></Route>
        <Route path="/products" element={<CategoriesComponent />}>
          <Route index element={<ProductsComponent />}></Route>
          <Route path="category/:categoryId" element={<ProductsComponent />}></Route>
          <Route path="product/:productId" />
          <Route path="basket" element={<BasketPage />}></Route>
        </Route>
      </Routes>
    </div>
  );
};

export default RoutesComponent;