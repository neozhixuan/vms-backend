provider "aws" {
  region = local.region
}

locals {
  name   = "ascode-cluster"
  region = "us-east-1"

  vpc_cidr = "10.123.0.0/16"
  azs      = ["us-east-1a", "us-east-1b"]

  public_subnets  = ["10.123.1.0/24", "10.123.2.0/24"]
  private_subnets = ["10.123.3.0/24", "10.123.4.0/24"]
  intra_subnets   = ["10.123.5.0/24", "10.123.6.0/24"]

  tags = {
    Example = local.name
  }
}

module "vpc" {
  source  = "terraform-aws-modules/vpc/aws"
  version = "~> 4.0"

  name = local.name
  cidr = local.vpc_cidr

  azs             = local.azs
  private_subnets = local.private_subnets
  public_subnets  = local.public_subnets
  intra_subnets   = local.intra_subnets

  enable_nat_gateway = true

  # Remove tags from subnets
  public_subnet_tags  = {}
  private_subnet_tags = {}
}

module "eks" {
  # Check the Terraform website, AWS module
  source  = "terraform-aws-modules/eks/aws"
  version = "19.15.1"

  cluster_name                   = local.name
  cluster_endpoint_public_access = true

  cluster_addons = {
    coredns = {
      most_recent = true
    }
    kube-proxy = {
      most_recent = true
    }
    vpc-cni = {
      most_recent = true
    }
  }

  # Let AWS configure the KMS Encryption Key
  # Can also configure additional policies

  vpc_id                   = module.vpc.vpc_id
  subnet_ids               = module.vpc.private_subnets
  control_plane_subnet_ids = module.vpc.intra_subnets

  # Can also configure cluster security group rules (port configuration)
  # Can also configure SSH configurations for ingress etc.
  # Can also configure node to node configurations
  # Can also configure self-managed node groups

  # EKS Managed Node Group(s)
  eks_managed_node_group_defaults = {
    ami_type       = "AL2_x86_64"
    instance_types = ["m5.large"]
    # Can also configure VPC security groups
    # Can also configure additional policies
    # attach_cluster_primary_security_group = true
  }

  eks_managed_node_groups = {
    ascode-cluster-wg = {
      min_size     = 1
      max_size     = 2
      desired_size = 1

      # Overwrites the instancetype configured above
      instance_types = ["t3.large"]
      capacity_type  = "SPOT"
      # Can also configure labels and taints
      tags = {
        ExtraTag = "helloworld"
      }
    }
  }

  # Add tags specifically for the Kubernetes security group
  tags = {
    # k8s_security_group = "sg-068bbd6eb36ff54e2"
  }
}

# Can also configure AWS Fargate
# Can also configure AWS Auth configmap
