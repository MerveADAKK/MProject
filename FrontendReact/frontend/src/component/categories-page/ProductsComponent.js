import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

const ProductsComponent = () => {
    const { categoryId } = useParams();
    const [cart, setCart] = useState(undefined);
    const urlList = `http://localhost:8080/product?category_id=${categoryId ?? 1} `;
    const [products, setProducts] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/cart/get/1")
            .then((data) => data.json())
            .then((cart) => {
                setCart(cart);
                console.log(cart);
            });
        fetch(urlList)
            .then((data) => data.json())
            .then((product) => {
                setProducts(product);
                console.log(product);
            });
    }, [urlList]);

    const addToBasket = (product) => {
        if (cart.cartStatus !== "COMPLETED") {
            const url = `http://localhost:8080/cart/add/1/${product.productId}?quantity=1`;
            fetch(url, { method: "POST" }).then((response) => response.json());
        } else alert("Sepetiniz onaylanmıştır!!");
    };
    return (
        <div>
            <div className="row row-cols-1 row-cols-md-4 g-2">
                {products.map((product) => (
                    <div key={product.productId} className="col">
                        <div className="card h-1">
                            <div className="d-flex justify-content-center">
                                <img
                                    src={require(`../../assets/${product.imagePath}`)}
                                    className="card-img-top"
                                    alt="" />
                            </div>
                            <div className="card-body">
                                <h5 className="card-title">{product.productName}</h5>
                                <small className="text-muted">{product.salesPrice}₺</small>
                            </div>
                            <div className="d-flex justify-content-end mb-3 mx-2">
                                <a href="/products/basket" className="default">
                                    <button
                                        onClick={() => addToBasket(product)}
                                        className="btn btn-outline-dark mx-2">
                                        Sepete Ekle
                                    </button>
                                </a>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ProductsComponent;
