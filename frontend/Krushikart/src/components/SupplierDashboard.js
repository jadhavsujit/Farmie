import React, { useState } from 'react';
import SupplierAddress from './SupplierAddress';
import AddProduct from './AddProduct';
import SuppliedProducts from './SuppliedProducts';

function SupplierDashboard() {
  const [menu, setMenu] = useState('');

  const handleMenuClick = (menu) => {
    setMenu(menu);
  };

  const renderMenu = () => {
    switch (menu) {
      case 'addAddress':
        return <SupplierAddress />;
      case 'addProduct':
        return <AddProduct />;
      case 'suppliedProducts':
        return <SuppliedProducts />;
      default:
        return null;
    }
  };

  return (
    <div>
      <button onClick={() => handleMenuClick('addAddress')}>Add Supplier Address</button>
      <button onClick={() => handleMenuClick('addProduct')}>Add Product</button>
      <button onClick={() => handleMenuClick('suppliedProducts')}>Get All Supplied Products</button>
      <br />
      {renderMenu()}
    </div>
  );
}

export default SupplierDashboard;
