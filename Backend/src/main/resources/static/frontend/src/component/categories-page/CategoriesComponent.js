import React, { useEffect, useState } from "react";
import "./style-product.css";
import { Outlet } from "react-router-dom";

const CategoriesComponent = () => {
  const urlList = "http://localhost:8080/category";

  const [categories, setCategories] = useState([]);

  useEffect(() => {
    fetch(urlList)
      .then((data) => data.json())
      .then((categories) => setCategories(categories));
  }, [urlList]);
  return (
    <div>
      <div className="header">
        <h1>Merve Alışveriş Sokağı</h1>
      </div>
      <nav id="sidebar"
        className="collapse d-lg-block sidebar collapse bg-white"
      >
        <div className="position-sticky">
          <div className="list-group list-group-flush mx-1 mt-2">
            <a
              href="/index"
              className="list-group-item list-group-item-action py-2 ripple bg-transparent text-white"
              aria-current="true"
            >
              <h6>
                Anasayfa
              </h6>
            </a>
            <a
              href="/products/basket"
              className="list-group-item list-group-item-action py-2 ripple bg-transparent text-white "
              aria-current="true"
            >
              <h6>
                Sepetim
              </h6>
            </a>
            <a
              href="/products/basket"
              className="list-group-item list-group-item-action py-2 ripple bg-transparent text-white mt-2"
              aria-current="true"
            >
              <h6 className="d-flex align-items-end">
                Kategoriler
              </h6>
            </a>

            {categories.map((category) => (
              <a
                key={category.categoryId}
                href={`/products/category/${category.categoryId}`}
                className=" list-group-item list-group-item-action py-3 ripple bg-transparent text-white border border-0 ms-4">
                <span className="d-flex align-items-center">
                  {category.categoryName}
                </span>
              </a>
            ))}
          </div>
        </div>
      </nav>
      <main style={{ marginTop: "40px" }}>
        <div className="mx-3">
          <Outlet />
        </div>
      </main>
    </div>
  );
};

export default CategoriesComponent;
