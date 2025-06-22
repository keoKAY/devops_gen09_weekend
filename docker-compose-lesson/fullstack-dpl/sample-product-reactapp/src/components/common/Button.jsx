// components/common/Button.jsx
const Button = ({ 
    children, 
    variant = 'primary', 
    size = 'medium',
    className = '',
    ...props 
  }) => {
    const baseClasses = "font-bold rounded focus:outline-none focus:ring-2";
    
    const variants = {
      primary: "bg-blue-500 hover:bg-blue-700 text-white focus:ring-blue-300",
      secondary: "bg-gray-200 hover:bg-gray-300 text-gray-800 focus:ring-gray-100",
      danger: "bg-red-500 hover:bg-red-700 text-white focus:ring-red-300",
    };
    
    const sizes = {
      small: "py-1 px-2 text-sm",
      medium: "py-2 px-4",
      large: "py-3 px-6 text-lg",
    };
    
    return (
      <button 
        className={`${baseClasses} ${variants[variant]} ${sizes[size]} ${className}`}
        {...props}
      >
        {children}
      </button>
    );
  };
  
  export default Button;