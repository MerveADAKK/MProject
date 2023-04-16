import React from "react";
import "../index-page/styles.css";

const IndexPage = () => {
    return (
        <div>
            <div class="header">
                <h1>Merve Alışveriş Sokağı</h1>
            </div>
            <ul className="navbar">
                <li className="active" role="presentation">
                    <a className="nav-link active" href="/products">
                        Ürünler
                    </a>
                </li>
                <li className="right" role="presentation">
                    <a className="nav-link active" href="/products/basket">
                        Sepetim
                    </a>
                </li>
            </ul>
            <div className="row">
                <div className="main">
                    <h1>Alışveriş Sokağı'na Hoşgeldiniz!</h1>
                </div>
            </div>
        </div>
    );
};

export default IndexPage;