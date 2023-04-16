import React, { useEffect, useState } from "react";
import "./style-basketPage.css";
import { useNavigate } from "react-router-dom";



const BasketPage = () => {
  const urlList = "http://localhost:8080/cart/get/1";
  const [cart, setCart] = useState(undefined);
  const [isCartUpdated, setIsCartUpdated] = useState(false);
  const [inputValue, setInputValue] = useState("");
  const [inputValueName, setInputValueName] = useState("");

  useEffect(() => {
    fetch(urlList)
      .then((data) => data.json())
      .then((cart) => {
        setCart(cart);
        console.log(cart);
      });
  }, [isCartUpdated]);

  let navigate = useNavigate();
  const goToProducts = () => {
    navigate("/products");
  };

  const handleCartChange = (event) => {
    setInputValue(event.target.value);
  };

  const handleCartChangeName = (event) => {
    setInputValueName(event.target.value);
  };


  const removeToCartProduct = (cartProduct) => {
    if (cart.cartStatus !== "COMPLETED") {
      const url = `http://localhost:8080/cart/remove/1/${cartProduct.product.productId}`;
      fetch(url, { method: "POST" }).then(() => {
        setIsCartUpdated(!isCartUpdated);
      });
    } else alert("Sepetiniz onaylanmıştır!!");
  };

  const updateCardNumber = (inputValue) => {
    const url = `http://localhost:8080/cart/update/1/${inputValue}`;
    fetch(url, {
      method: "POST",
    }).then(() => {
      setIsCartUpdated(!isCartUpdated);
    })
  };

  const updateCustomerName = (inputValueName) => {
    const url = `http://localhost:8080/cart/updateName/1/${inputValueName}`;
    fetch(url, {
      method: "POST",
    }).then(() => {
      setIsCartUpdated(!isCartUpdated);
    })
  };

  const checkoutBasket = () => {
    let url = `http://localhost:8080/cart/checkout/1`;
    fetch(url, {
      method: "POST",
    }).then(() => {
      setIsCartUpdated(!isCartUpdated);
    });
    if (cart.cartStatus === "COMPLETED") {
      alert("Sepetiniz onaylanmıştır..");
    }
  };

  const plusQuantity = (cartProduct) => {
    if (cart.cartStatus !== "COMPLETED") {
      const url = `http://localhost:8080/cart/add/1/${cartProduct.product.productId}?quantity=1`;
      fetch(url, { method: "POST" }).then(() => {
        setIsCartUpdated(!isCartUpdated);
      });
    } else alert("Sepetiniz onaylanmıştır!!");
  };

  const minusQuantity = (cartProduct) => {
    if (cart.cartStatus !== "COMPLETED") {
      const url = `http://localhost:8080/cart/minus/1/${cartProduct.product.productId}?quantity=1`;
      fetch(url, { method: "POST" }).then(() => {
        setIsCartUpdated(!isCartUpdated);
      });
    } else alert("Sepetiniz onaylanmıştır!!");
  };

  return (
    <div>

      <h3 className="fw-bold d-flex"> Sepetim</h3>{" "}
      <div className="cart_section">
        <div className="container-fluid">
          <div className="row">
            <div className="col-lg-10 offset-lg-1">
              <div className="cart_container">
                <div className="cart_title fs-3 d-flex align-items-baseline">
                  <p>
                    {" "}
                    Kullanıcı: {cart && (cart.customerName)}{" "}
                    Kart Numarası: {cart && (cart.cardNumber)}{" "}
                  </p>
                </div>

                <div className="cart_container">
                  Yeni Kart Ekle (Kart Numaranızı Giriniz)
                  <input type="text" value={inputValue} onChange={handleCartChange} />
                  <button
                    onClick={() => updateCardNumber(inputValue)}
                    type="button"
                    className="button cart_button_checkout2"
                  >
                    Onayla
                  </button>
                </div>

                <div className="cart_container">
                  İsim Güncelle (İsminizi Giriniz)
                  <input type="text" value={inputValueName} onChange={handleCartChangeName} />
                  <button
                    onClick={() => updateCustomerName(inputValueName)}
                    type="button"
                    className="button cart_button_checkout2"
                  >
                    Onayla
                  </button>
                </div>


                {cart && cart.cartProducts
                  .filter(cartProduct => cartProduct.salesQuantity > 0)
                  .sort((a, b) =>
                    a.product.productName.localeCompare(b.product.productName)
                  )
                  .map((cartProduct, index) => (
                    <div className="cart_items" key={index}>
                      <ul className="cart_list">
                        <button
                          onClick={() => removeToCartProduct(cartProduct)}
                          style={{ cursor: "pointer" }}
                          className="bi bi-x mt-4 me-2 fs-5 d-flex justify-content-head">
                        </button>
                        <li className="cart_item clearfix">
                          <div className="cart_item_image">
                            <img
                              src={require(`../../assets/${cartProduct.product.imagePath}`)}
                              className="card-img-top"
                              alt="" />
                          </div>
                          <div className="cart_item_info d-flex flex-md-row flex-column justify-content-between">
                            <div className="cart_item_name cart_info_col">
                              <div className="cart_item_title">Ürün</div>
                              <div className="cart_item_text">
                                {cartProduct.product.productName}
                              </div>
                            </div>

                            <div className="cart_item_quantity cart_info_col">
                              <div className="cart_item_title"> Miktar</div>
                              <div className="cart_item_text">
                                {cartProduct.salesQuantity}{" "}
                                <i
                                  onClick={() => minusQuantity(cartProduct)}
                                  style={{ cursor: "pointer" }}
                                  className="bi bi-dash-square red-color ms-2"
                                ></i>{" "}

                                <i
                                  onClick={() => plusQuantity(cartProduct)}
                                  style={{ cursor: "pointer" }}
                                  className="bi bi-plus-square red-color me-2"
                                ></i>
                              </div>

                            </div>
                            <div className="cart_item_price cart_info_col">
                              <div className="cart_item_title">Fiyat</div>
                              <div className="cart_item_text">
                                {cartProduct.product.salesPrice}

                                ₺
                              </div>
                            </div>
                            <div className="cart_item_total cart_info_col">
                              <div className="cart_item_title">Toplam</div>
                              <div className="cart_item_text">

                                {cartProduct.product.salesPrice * cartProduct.salesQuantity}₺
                              </div>
                            </div>
                          </div>
                        </li>
                      </ul>
                    </div>
                  ))}
                <div className="order_total">
                  <div className="order_total_content">
                    <div className="order_total_amount">
                      <div className="order_total_title"> Toplam Ürün Sayısı:{" "}</div>
                      {cart && cart.cartProducts
                        .map((cartProduct) => cartProduct.salesQuantity)
                        .reduce((a, b) => a + b, 0)}{" "}
                    </div>
                    <div className="order_total_amount">
                      <div className="order_total_title"> Toplam Fiyat:</div>


                      {cart &&
                        cart.cartProducts
                          .map(
                            (cartProduct) =>
                              cartProduct.salesQuantity * cartProduct.product.salesPrice
                          )
                          .reduce((a, b) => a + b, 0)}₺
                    </div>
                  </div>
                </div>
                <div className="cart_buttons">
                  {" "}
                  <button
                    onClick={goToProducts}
                    type="button"
                    className="button cart_button_clear"
                  >
                    Alışverişe Devam Et
                  </button>{" "}
                  {cart && cart.cartStatus !== "COMPLETED" ? (
                    <button
                      onClick={checkoutBasket}
                      type="button"
                      className="button cart_button_checkout"
                    >
                      Sepeti Onayla
                    </button>
                  ) : (
                    <button
                      type="button"
                      className="buttonEnd cart_button_checkoutEnd"
                      disabled
                    >
                      Sepet Onaylandı
                    </button>
                  )}



                  {cart && cart.cartProducts
                    .filter(cartProduct => cartProduct.salesQuantity == 0)
                    .sort((a, b) =>
                      a.product.productName.localeCompare(b.product.productName)
                    )

                    .map((cartProduct, index) => (

                      <div className="cart_items" key={index}>
                        <h5 className="fw-bold d-flex"> Eskiden Sepetine Eklediğin Ürünü Tekrar Eklemek İster Misin ?</h5>{" "}
                        <ul className="cart_list">

                          <li className="cart_item clearfix">
                            <div className="cart_item_image">
                              <img
                                src="https://media.istockphoto.com/id/1140017050/tr/vekt%C3%B6r/daire-simgesi-y%C3%BCkleniyor-ilerleme-vekt%C3%B6r-simgesi-y%C3%BCkleniyor-g%C3%BCncelleme-simgesi.jpg?s=170667a&w=0&k=20&c=8WHOG50xoEDpY8btEeOSbNZzGhtGDSsHjcy1crus-go="
                              />
                            </div>
                            <div className="cart_item_info d-flex flex-md-row flex-column justify-content-between">
                              <div className="cart_item_name cart_info_col">
                                <div className="cart_item_title">Ürün</div>
                                <div className="cart_item_text">
                                  {cartProduct.product.productName}
                                </div>
                              </div>


                              <div className="cart_item_price cart_info_col">
                                <div className="cart_item_title">Fiyat</div>
                                <div className="cart_item_text">
                                  {cartProduct.product.salesPrice}
                                  ₺
                                </div>
                              </div>

                              <div className="cart_item_quantity cart_info_col">
                                <div className="cart_item_title"> Ekle</div>
                                <div className="cart_item_text">
                                  <i
                                    onClick={() => plusQuantity(cartProduct)}
                                    style={{ cursor: "pointer" }}
                                    className="bi bi-plus-square red-color me-2"
                                  ></i>
                                </div>

                              </div>

                            </div>
                          </li>
                        </ul>
                      </div>
                    ))}


                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div >
  );
};
export default BasketPage;
