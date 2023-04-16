import React from "react";
import { useEffect } from "react";
import { useState } from "react";
import { Link } from "react-router-dom";

const Category = () => {
  const urlList = "http://localhost:8080/category";
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    fetch(urlList)
      .then((data) => data.json())
      .then((categories) => setCategories(categories));
  }, [urlList]);
  return (
    <div>
      <div className="card" style={{ width: "18rem" }}>
        <div className="card-header">Kategoriler</div>
        <ul className="list-group list-group-flush">
          {categories.map((category) => (
            <Link to={"/products/" + category.categoryId}>
              <li className="list-group-item">{category.categoryName}</li>
            </Link>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default Category;
