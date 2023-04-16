import Category from "./category/Category";
import { Outlet } from "react-router";

const Body = () => {
  return (
    <div className="row">
      <div className="col-md-3">
        <Category />
      </div>
      <div className="col-md-9">
        <Outlet />
      </div>
    </div>
  );
};

export default Body;
