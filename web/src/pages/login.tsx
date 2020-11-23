import React from "react";
import { Form, Formik } from "formik";
import { useQuery } from "react-query";

interface loginProps {}

export const login: React.FC<loginProps> = ({}) => {
  const { data, error, isLoading, isError } = useQuery("login");
  return (
    <>
      <Formik
        initialValues={{
          email: "",
          password: "",
        }}
        onSubmit={async (values) => {}}
      />
      <Form></Form>
    </>
  );
};
